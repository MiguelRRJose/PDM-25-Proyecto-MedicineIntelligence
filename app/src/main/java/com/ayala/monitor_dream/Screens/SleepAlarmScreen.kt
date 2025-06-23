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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ayala.monitor_dream.Composables.TimeCard
import com.ayala.monitor_dream.Composables.showTimePickerDialog
import com.ayala.monitor_dream.utils.formatTimeAMPM

@Composable
fun SleepAlarmScreen(
    onSetAlarmClick: (Int, Int, Int) -> Unit
) {
    var sleepHour by remember { mutableStateOf(22) }
    var sleepMinute by remember { mutableStateOf(0) }

    var alarmHour by remember { mutableStateOf(6) }
    var alarmMinute by remember { mutableStateOf(0) }

    var reminderMinutes by remember { mutableStateOf("15") }

    val sleepTotalMinutes = sleepHour * 60 + sleepMinute
    val alarmTotalMinutes = alarmHour * 60 + alarmMinute
    val duration = if (alarmTotalMinutes - sleepTotalMinutes < 0)
        (alarmTotalMinutes - sleepTotalMinutes + 24 * 60)
    else
        alarmTotalMinutes - sleepTotalMinutes

    val sleepDuration = duration / 60

    var showSleepPicker by remember { mutableStateOf(false) }

    if (showSleepPicker) {
        showTimePickerDialog(sleepHour, sleepMinute) { h, m ->
            sleepHour = h
            sleepMinute = m
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
        Text("Meta de sueño", color = Color.White, fontSize = 24.sp)
        Spacer(modifier = Modifier.height(16.dp))

        TimeCard("🛏️ Tiempo de dormir", formatTimeAMPM(sleepHour, sleepMinute)) {
            showSleepPicker = true
        }

        TimeCard("⏰ Alarma", formatTimeAMPM(alarmHour, alarmMinute))
        {
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
            onSetAlarmClick(sleepHour, sleepMinute, reminder)
        }) {
            Text("Establecer alarma")
        }
    }
}
