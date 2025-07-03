package com.ayala.monitor_dream.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import com.ayala.monitor_dream.R
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ayala.monitor_dream.composables.ButtonSleepScreen
import com.ayala.monitor_dream.composables.CardIcon
import com.ayala.monitor_dream.composables.CircularTimeSleepCountdown
import com.ayala.monitor_dream.composables.PersonalBackground
import com.ayala.monitor_dream.composables.PersonalSpacer
import com.ayala.monitor_dream.composables.SelectedImage
import com.ayala.monitor_dream.composables.TextEspecialPersColr
import com.ayala.monitor_dream.composables.TextSubPersColr
import com.ayala.monitor_dream.composables.TextTittlePersColr
import com.ayala.monitor_dream.navigation.AlarmP
import com.ayala.monitor_dream.navigation.SleepDetail
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
            TextEspecialPersColr("Feliz descanso!",Color.White)

            PersonalSpacer(8)

            TextTittlePersColr(formattedTime,Color.White)

            PersonalSpacer(16)

            if (showPersonalizedTime){

                CircularTimeSleepCountdown(initialTime = sleepTime, indicatorSize = 150.dp, strokeWidth = 10.dp)

                PersonalSpacer(16)

            } else {

                SelectedImage(R.drawable.astro_durmiendo, "Astronauta dormido")

                PersonalSpacer(16)
            }

            CardIcon(R.drawable.alarm_white_1,"Alarma: ${formatTimeAMPM(alarmTime.hour, alarmTime.minute)} ",Color.White)

            PersonalSpacer(30)

            Column()
            {

                if (!showElements) {

                    ButtonSleepScreen("INICIAR SUEÑO",20)
                    { showElements = true
                        showPersonalizedTime = true
                    }

                    PersonalSpacer(16)

                    ButtonSleepScreen("DETALLES",20)
                    {navController.navigate(SleepDetail)}

                } else
                {
                    ButtonSleepScreen("DESPERTAR",20)
                    {navController.popBackStack(AlarmP, inclusive = false)}
                }

            }

        }

    }
}





