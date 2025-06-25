package com.ayala.monitor_dream.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ayala.monitor_dream.ViewModel.SleepViewModel
import com.ayala.monitor_dream.model.AlarmData
import com.ayala.monitor_dream.utils.formatTimeAMPM
import kotlinx.coroutines.delay
import parseTimeToCalendar
import java.util.Calendar

@Composable
fun SleepTrackingScreen(
    navController: NavController,
    viewModel: SleepViewModel,
    alarmData: AlarmData
) {

    val savedAlarmTime by viewModel.alarmUser.collectAsState()
    val alarmaDate = remember(alarmData) { formatTimeAMPM(alarmData) }

    val wakeUpTime = viewModel.alarmTime.collectAsState().value
    val despierta = remember(alarmData) { formatTimeAMPM(alarmData) }

    var timeLeftMillis by remember { mutableLongStateOf(0L) }

    val wakeUpCalendar = remember(wakeUpTime) {
        parseTimeToCalendar(wakeUpTime)
    }

    LaunchedEffect(wakeUpTime) {
        while (true) {
            val now = Calendar.getInstance()
            timeLeftMillis = wakeUpCalendar.timeInMillis - now.timeInMillis
            delay(1000)
        }
    }

    val totalSeconds = timeLeftMillis / 1000
    val hours = totalSeconds / 3600
    val minutes = (totalSeconds % 3600) / 60
    val seconds = totalSeconds % 60
    val countdown = String.format("%02d:%02d:%02d", hours, minutes, seconds)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0A1F33))
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            //Unicamente creado para comprobar que la alarma se guarda correctamente
            Text(
                text = "Alarma guardada: $alarmaDate",
                color = Color.White,
                fontSize = 16.sp,
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "😴 Durmiendo...",
                fontSize = 24.sp,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Despertar a las: $despierta",
                fontSize = 18.sp,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Faltan: $countdown",
                fontSize = 32.sp,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(onClick = {
                navController.popBackStack()
            }) {
                Text("DESPERTAR")
            }
        }
    }
}

