package com.ayala.monitor_dream.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import com.ayala.monitor_dream.R
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
import com.ayala.monitor_dream.navigation.AlarmP
import com.ayala.monitor_dream.navigation.SleepTrackingOG
import com.ayala.monitor_dream.viewModel.SleepViewModel
import com.ayala.monitor_dream.utils.formatTimeAMPM
import com.ayala.monitor_dream.utils.formatTimeAMPM2
import kotlinx.coroutines.delay


@Composable
fun SleepScreen(
    viewModel: SleepViewModel,
    navController: NavController,

) {

    var showElements by remember { mutableStateOf(false) }

    //ViewModel
    val alarmTime by viewModel.alarmTime.collectAsState()

    val startTime by viewModel.startTime.collectAsState()

    //Para mostrar el reloj en pantalla
    val formattedTime = formatTimeAMPM2(startTime)

    LaunchedEffect(Unit) {
        while (true) {
            delay(1000)
            viewModel.setSleepTimeCurrentDeviceTime()
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
                text = "Buenas noches",
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

            TimeCard("Alarma: ", formatTimeAMPM(alarmTime), R.drawable.alarm_white_1){}

            Spacer(modifier = Modifier.height(30.dp))


            if (!showElements) {

                ButtonSleepScreen("INICIAR SUEÑO")
                { showElements = true}
                ButtonSleepScreen("DETALLES")
                {navController.navigate(SleepTrackingOG)}

            } else
            {
                ButtonSleepScreen("DESPERTAR")
                {navController.popBackStack(AlarmP, inclusive = false)}
            }

        }
    }
}





