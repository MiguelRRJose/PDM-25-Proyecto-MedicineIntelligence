package com.ayala.monitor_dream.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ayala.monitor_dream.R
import com.ayala.monitor_dream.composables.PersonalBackground
import com.ayala.monitor_dream.composables.SelectedImage
import com.ayala.monitor_dream.composables.TimeCard
import com.ayala.monitor_dream.navigation.AlarmP
import com.ayala.monitor_dream.navigation.SleepY
import com.ayala.monitor_dream.utils.convertMillisToActualData
import com.ayala.monitor_dream.utils.formatTimeAMPM
import com.ayala.monitor_dream.utils.formatTimeAMPM2
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


    val startTimeMillis: Long = System.currentTimeMillis()
    val currentTime = convertMillisToActualData(startTimeMillis)
    val formattedTime = formatTimeAMPM2(currentTime)


    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {

        PersonalBackground(R.drawable.backgroud_1,"background")

        Column(modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally) {

            Text(
                text = "Buenas noches",
                color = Color.White,
                fontSize = 20.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

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

            Spacer(modifier = Modifier.height(24.dp))

            Button(onClick = {
                navController.popBackStack()
            }) {
                Text("DESPERTAR")
            }
        }
    }
}

