package com.ayala.monitor_dream.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ayala.monitor_dream.Composables.TimeCard
import com.ayala.monitor_dream.Composables.showTimePickerDialog
import com.ayala.monitor_dream.ViewModel.SleepViewModel
import com.ayala.monitor_dream.model.ActualTime
import com.ayala.monitor_dream.model.AlarmData
import com.ayala.monitor_dream.utils.convertMillisToActualData
import com.ayala.monitor_dream.utils.formatTimeAMPM
import com.ayala.monitor_dream.utils.formatTimeAMPM2

@Composable
fun SleepAlarmScreen(
    viewModel: SleepViewModel,
    onSetAlarmClick: (AlarmData, ActualTime,Int) -> Unit
) {

    //Dato ViewModel
    val alarmTime by viewModel.alarmTime.collectAsState()


    //Lanzamiento del dato reloj sistema Para personalizar
    val startTimeMillis: Long = System.currentTimeMillis()
    var currentTime = convertMillisToActualData(startTimeMillis)


    //Dato Dormir
    var sleepHour by remember { mutableStateOf(currentTime.hour)}
    var sleepMinute by remember { mutableStateOf(currentTime.minute)}

    //Dato Alarma
    var alarmHour by remember { mutableStateOf(alarmTime.hour) }
    var alarmMinute by remember { mutableStateOf(alarmTime.minute) }

    //Dato recordatorio
    var reminderMinutes by remember { mutableStateOf("15") }

    //Para la ventana de tiempo
    var showSleepPicker by remember { mutableStateOf(false) }

    var personalTime by remember { mutableStateOf(false)}

    //Para tiempo Usuario Personal
    //var systemTime = remember {currentTime}

    //Para el tiempo sistema

    val storedSleepTime by viewModel.startTime.collectAsState()

    val currentSleepTime = storedSleepTime ?: currentTime


    //Determinar que ventana de tiempo se utilizará
    var editingTime by remember { mutableStateOf("sleep") } // "sleep" o "alarm"

    LaunchedEffect(alarmTime) {
        alarmHour = alarmTime.hour
        alarmMinute = alarmTime.minute
    }

    //Transformaciones de tiempo (Posible cambio para el viewModel)
    val sleepTotalMinutes = sleepHour * 60 + sleepMinute
    val alarmTotalMinutes = alarmHour * 60 + alarmMinute

    val duration = if (alarmTotalMinutes - sleepTotalMinutes < 0)
        (alarmTotalMinutes - sleepTotalMinutes + 24 * 60)
    else
        alarmTotalMinutes - sleepTotalMinutes

    val sleepDuration = duration / 60


    if (showSleepPicker) {
        // Mostrar picker con valores actuales según lo que se edite
        val hourInit =
            if (editingTime == "sleep") sleepHour
                else alarmHour

        val minuteInit =
            if (editingTime == "sleep") sleepMinute
                else alarmMinute

        //Ventana de tiempo
        showTimePickerDialog(hourInit, minuteInit) { h, m ->
            if (editingTime == "sleep") {
                sleepHour = h
                sleepMinute = m

                if(personalTime)
                {
                    val newCustomTime = ActualTime(h,m)
                    viewModel.setStartTime(newCustomTime)
                    personalTime = false
                }

            } else {
                alarmHour = h
                alarmMinute = m
                viewModel.setAlarmTime(AlarmData(h, m))  // Guarda la alarma en ViewModel
            }
            showSleepPicker = false
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0A1F33))
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        Text("Alarma_Personal_Salud", color = Color.White, fontSize = 24.sp)
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {

            if(storedSleepTime != null)
            {
                personalTime = true
                viewModel.setStartTime(currentTime)

            } else
            {
                showSleepPicker = true
                editingTime = "sleep"
            }

        }) {

            if(storedSleepTime != null)
            Text("Restablecer a hora del sistema\n${formatTimeAMPM2(currentTime)}")
            else
                Text("Personalizar tiempo de sueño\n${formatTimeAMPM2(currentSleepTime)}")
        }

        TimeCard("🛏️ Tiempo de dormir ",
            formatTimeAMPM2(currentSleepTime),
            )
        {
            editingTime = "sleep"
            showSleepPicker = true
        }

        TimeCard("⏰ Alarma ",
            formatTimeAMPM(AlarmData(alarmHour, alarmMinute)))
        {
            editingTime = "alarm"
            showSleepPicker = true
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "🕒 Meta para dormir: ${sleepDuration} h",
            color = Color.White,
            fontSize = 18.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = reminderMinutes,
            onValueChange = { reminderMinutes = it },
            label = { Text("Recordar dentro:") },
            trailingIcon = { Text("min") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.White,
                focusedBorderColor = Color.White,
                unfocusedTextColor = Color.White,
                unfocusedBorderColor = Color.White
            )
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = {
            val reminder = reminderMinutes.toIntOrNull() ?: 15
            val alarmData = AlarmData(alarmHour, alarmMinute)

            //Definición para que sea posible guardar los datos antes del onClick
            onSetAlarmClick(alarmData, currentSleepTime,reminder)

        }) {
            Text("Establecer alarma")
        }
    }
}
