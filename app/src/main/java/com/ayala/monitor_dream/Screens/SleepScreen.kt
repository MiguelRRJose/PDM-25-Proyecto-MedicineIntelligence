package com.ayala.monitor_dream.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import com.ayala.monitor_dream.R
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ayala.monitor_dream.navigation.SleepTrackingOG
import com.ayala.monitor_dream.viewModel.SleepViewModel
import com.ayala.monitor_dream.utils.convertMillisToActualData
import com.ayala.monitor_dream.utils.formatTimeAMPM
import com.ayala.monitor_dream.utils.formatTimeAMPM2
import kotlinx.coroutines.delay


@Composable
fun SleepScreen(
    viewModel: SleepViewModel,
    navController: NavController,

) {
    //ViewModel
    val alarmTime by viewModel.alarmTime.collectAsState()
    val storedSleepTime by viewModel.startTime.collectAsState()
    val sleepDuration by viewModel.duration.collectAsState()

    //Para mostrar un reloj en pantalla
    val startTimeMillis: Long = System.currentTimeMillis()
    val currentTime = convertMillisToActualData(startTimeMillis)
    val formattedTime = formatTimeAMPM2(currentTime)

    LaunchedEffect(Unit) {
        while (true) {
            delay(1000)
            startTimeMillis
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0A1F33))
            .padding(24.dp)
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Hora de una buena siesta amigo!",
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

            Image(
                painter = painterResource(id = R.drawable.astro_durmiendo),
                contentDescription = "Astronauta dormido :3" ,
                modifier = Modifier.size(160.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = " ${formatTimeAMPM2(storedSleepTime)}",
                color = Color.White,
                fontSize = 16.sp
            )

            Text(
                text = "⏰ ${formatTimeAMPM(alarmTime)}",
                color = Color.White,
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Tiempo de sueño: ${sleepDuration.hour} h\n" +
                        "${sleepDuration.minute} min",
                color = Color.White,
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    navController.navigate(SleepTrackingOG)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
            ) {
                Text("INICIAR SUEÑO")
            }

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Volver")
            }

        }
    }
}




