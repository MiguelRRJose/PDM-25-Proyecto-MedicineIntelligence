package com.ayala.monitor_dream.screens

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
import com.ayala.monitor_dream.viewModel.SleepViewModel
import kotlinx.coroutines.delay
import com.ayala.monitor_dream.utils.parseTimeToCalendar
import com.ayala.monitor_dream.utils.parseTimeToCalendar2

@Composable
fun SleepTrackingScreen(
    navController: NavController,
    viewModel: SleepViewModel,
) {
    //Dato ViewModel
    val alarmTime = viewModel.alarmTime.collectAsState().value

    val startSleepTime = viewModel.startTime.collectAsState().value ?: return

    val currenTimeMillis = remember {mutableStateOf(System.currentTimeMillis())}

    //var sleepDurationMillis by remember { mutableLongStateOf(0L) }

    //val safeSleepDurationMillis = sleepDurationMillis.coerceAtLeast(0L)

var sleepDurationMillis by remember { mutableLongStateOf(0L) }
    var timeLeftMillis by remember { mutableLongStateOf(0L) }
    var totalSleepMillis by remember { mutableLongStateOf(0L) }



    LaunchedEffect(Unit) {
        while(true) {

            val sleepCalendar = parseTimeToCalendar2(startSleepTime)
            val wakeUpCalendar = parseTimeToCalendar(alarmTime)

            val nowMillis = System.currentTimeMillis()

            val startMillis = sleepCalendar.timeInMillis
            val endMillis = wakeUpCalendar.timeInMillis

            sleepDurationMillis = (nowMillis - startMillis).coerceAtLeast(0L)
            timeLeftMillis = (endMillis - nowMillis).coerceAtLeast(0L)
            totalSleepMillis = (endMillis - startMillis).coerceAtLeast(0L)

            delay(1000L)
        }
    }


    //Operaciones para el tiempo
    val totalSleepSeconds = totalSleepMillis / 1000

    val Sleephours = totalSleepSeconds / 3600
    val Sleepminutes = (totalSleepSeconds % 3600) / 60
    val Sleepseconds = totalSleepSeconds % 60

    val Sleepcountdown = String.format("%02d:%02d:%02d", Sleephours, Sleepminutes, Sleepseconds)

    val totalSeconds = sleepDurationMillis / 1000

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

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "😴 Durmiendo...",
                fontSize = 24.sp,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Despertar a las: ${alarmTime.hour}:${alarmTime.minute}\n" +
                        "Hora programada:${startSleepTime.hour}:${startSleepTime.minute}",

                fontSize = 18.sp,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Tiempo durmiendo: $countdown\n"
                        + "Tiempo transcurrido: $Sleepcountdown",
                fontSize = 20.sp,
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

