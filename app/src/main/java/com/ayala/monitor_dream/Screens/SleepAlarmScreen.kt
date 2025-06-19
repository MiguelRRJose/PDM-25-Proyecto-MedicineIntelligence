package com.ayala.monitor_dream.Screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ayala.monitor_dream.Composables.TimeCard
import com.ayala.monitor_dream.Composables.showTimePickerDialog
import java.time.LocalTime

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SleepAlarmScreen(
    onSetAlarmClick: (LocalTime, LocalTime, Int) -> Unit
) {
    var sleepTime by remember { mutableStateOf(LocalTime.of(22, 0)) }
    var alarmTime by remember { mutableStateOf(LocalTime.of(6, 0)) }

    var reminderMinutes by remember { mutableStateOf("15") }

    val durationMinutes = alarmTime.toSecondOfDay() / 60 - sleepTime.toSecondOfDay() / 60
    val fixedDuration = if (durationMinutes < 0) durationMinutes + 24 * 60 else durationMinutes
    val sleepDuration = fixedDuration / 60

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0A1F33))
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Meta de sueño", color = Color.White, fontSize = 24.sp)
        Spacer(modifier = Modifier.height(16.dp))

        TimeCard ("🛏️ Tiempo de dormir", sleepTime ) {

            showTimePickerDialog(sleepTime) { sleepTime = it }
        }

        TimeCard("⏰ Alarma", alarmTime) {

            showTimePickerDialog(alarmTime) { alarmTime = it }
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
            onSetAlarmClick(sleepTime, alarmTime, reminder)
        }) {
            Text("Establecer alarma")
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun SleepAlarmScreenPreview() {
    SleepAlarmScreen(onSetAlarmClick = { _, _, _ -> })
}



