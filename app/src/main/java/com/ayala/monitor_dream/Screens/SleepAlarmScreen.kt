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
import com.ayala.monitor_dream.model.AlarmData
import com.ayala.monitor_dream.utils.formatTimeAMPM

@Composable
fun SleepAlarmScreen(
    viewModel: SleepViewModel,
    onSetAlarmClick: (AlarmData, Int) -> Unit
) {

    //Dato ViewModel
    val alarmTime by viewModel.alarmTime.collectAsState()

    //Dato Dormir
    var sleepHour by remember { mutableStateOf(22) }
    var sleepMinute by remember { mutableStateOf(0) }

    //Dato Alarma
    var alarmHour by remember { mutableStateOf(alarmTime.hour) }
    var alarmMinute by remember { mutableStateOf(alarmTime.minute) }

    //Dato recordatorio
    var reminderMinutes by remember { mutableStateOf("15") }

    //Para la ventana de tiempo
    var showSleepPicker by remember { mutableStateOf(false) }

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

        Text("Doctor sleep alarm", color = Color.White, fontSize = 24.sp)
        Spacer(modifier = Modifier.height(16.dp))

        TimeCard("🛏️ Tiempo de dormir ",
            formatTimeAMPM(AlarmData(sleepHour, sleepMinute)))
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
            onSetAlarmClick(alarmData, reminder)

        }) {
            Text("Establecer alarma")
        }
    }
}
