package com.ayala.monitor_dream.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import com.ayala.monitor_dream.R
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ayala.monitor_dream.composables.ButtonSleepScreen
import com.ayala.monitor_dream.composables.PersonalBackground
import com.ayala.monitor_dream.composables.SelectedImage
import com.ayala.monitor_dream.composables.TimeCard
import com.ayala.monitor_dream.composables.TimeSleepCountdownBar
import com.ayala.monitor_dream.navigation.AlarmP
import com.ayala.monitor_dream.navigation.SleepSummary
import com.ayala.monitor_dream.viewModel.SleepViewModel
import com.ayala.monitor_dream.utils.formatTimeAMPM
import com.ayala.monitor_dream.utils.formatTimeAMPM3
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
    val formattedTime = formatTimeAMPM3(deviceTime)

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
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Feliz descanso!",
                color = Color.White,
                fontSize = 20.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = formattedTime,
                color = Color.White,
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(16.dp))

            SelectedImage(R.drawable.astro_durmiendo, "Astronauta dormido")

            Spacer(modifier = Modifier.height(16.dp))

            if (showPersonalizedTime){

                Text("Tiempo de sueño profundo restante: ",
                    color = Color.White,
                    style = MaterialTheme.typography.titleMedium
                )
                TimeSleepCountdownBar(
                    initialTime = sleepTime,
                    modifier = Modifier.padding(horizontal = 32.dp,vertical = 8.dp))
            } else {
                TimeCard("Tiempo de sueño profundo: ", "${sleepTime.hour} h: ${sleepTime.minute} min", R.drawable.alarm_white_1){}
            }

            TimeCard("Alarma: ", formatTimeAMPM(alarmTime), R.drawable.alarm_white_1){}

            Spacer(modifier = Modifier.height(30.dp))

            if (!showElements) {

                ButtonSleepScreen("INICIAR SUEÑO")
                { showElements = true
                    showPersonalizedTime = true
                }
                ButtonSleepScreen("DETALLES")
                {navController.navigate(SleepSummary)}

            } else
            {
                ButtonSleepScreen("DESPERTAR")
                {navController.popBackStack(AlarmP, inclusive = false)}
            }

        }
    }
}





