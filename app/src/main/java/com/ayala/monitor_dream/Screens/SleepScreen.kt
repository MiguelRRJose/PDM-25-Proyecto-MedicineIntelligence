package com.ayala.monitor_dream.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import com.ayala.monitor_dream.R
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ayala.monitor_dream.composables.ButtonSleepScreen
import com.ayala.monitor_dream.composables.CircularTimeSleepCountdown
import com.ayala.monitor_dream.composables.PersonalBackground
import com.ayala.monitor_dream.composables.SelectedImage
import com.ayala.monitor_dream.composables.TextTittle
import com.ayala.monitor_dream.composables.TimeCard
import com.ayala.monitor_dream.navigation.AlarmP
import com.ayala.monitor_dream.navigation.SleepDetail
import com.ayala.monitor_dream.navigation.SleepSummary
import com.ayala.monitor_dream.viewModel.SleepViewModel
import com.ayala.monitor_dream.utils.formatTimeAMPM
import kotlinx.coroutines.delay


@Composable
fun SleepScreen(
    viewModel: SleepViewModel,
    navController: NavController,

) {

    var showElements by remember { mutableStateOf(false) }
    var showPersonalizedTime by remember { mutableStateOf(false) }

    //ViewModel
    val alarmTime by viewModel.alarmTime.collectAsState()

    val sleepTime by viewModel.duration.collectAsState()

    val deviceTime by viewModel.deviceTimeFlow.collectAsState()


    //Para mostrar el reloj en pantalla
    val formattedTime = formatTimeAMPM(deviceTime.hour, deviceTime.minute)

    LaunchedEffect(Unit) {
        while (true) {

            viewModel.setDeviceTime()

            delay(1000)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {

        PersonalBackground(R.drawable.backgroud_1,"background")

        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)

        ) {
            TextTittle("Feliz descanso!")

            Spacer(modifier = Modifier.height(8.dp))

            TextTittle(formattedTime)

            Spacer(modifier = Modifier.height(16.dp))

            if (showPersonalizedTime){

                CircularTimeSleepCountdown(initialTime = sleepTime, indicatorSize = 150.dp, strokeWidth = 10.dp)

                Spacer(modifier = Modifier.height(16.dp))

            } else {

                SelectedImage(R.drawable.astro_durmiendo, "Astronauta dormido")

                Spacer(modifier = Modifier.height(16.dp))
            }

            TimeCard("Alarma: ", formatTimeAMPM(alarmTime.hour, alarmTime.minute) , R.drawable.alarm_white_1){}

            Spacer(modifier = Modifier.height(30.dp))

            Column()
            {

                if (!showElements) {

                    ButtonSleepScreen("INICIAR SUEÑO")
                    { showElements = true
                        showPersonalizedTime = true
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    ButtonSleepScreen("DETALLES")
                    {navController.navigate(SleepDetail)}

                } else
                {
                    ButtonSleepScreen("DESPERTAR")
                    {navController.popBackStack(AlarmP, inclusive = false)}
                }

            }

        }

    }
}





