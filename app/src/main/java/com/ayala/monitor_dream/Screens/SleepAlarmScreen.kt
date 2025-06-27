package com.ayala.monitor_dream.screens

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ayala.monitor_dream.composables.ShowTimePickerDialog
import com.ayala.monitor_dream.composables.TimeCard
import com.ayala.monitor_dream.navigation.ActualTime
import com.ayala.monitor_dream.navigation.AlarmData
import com.ayala.monitor_dream.navigation.TimeSleep
import com.ayala.monitor_dream.utils.formatTimeAMPM
import com.ayala.monitor_dream.utils.formatTimeAMPM2
import com.ayala.monitor_dream.viewModel.SleepViewModel


@Composable
fun SleepAlarmScreen(
    viewModel: SleepViewModel,
    onSetAlarmClick: (AlarmData, ActualTime, TimeSleep) -> Unit
) {

    var showPersonalizedTimeCard by remember { mutableStateOf(false) }

    //Datos ViewModel
    val alarmTime by viewModel.alarmTime.collectAsState()
    val storedSleepTime by viewModel.startTime.collectAsState()
    val sleepDuration by viewModel.duration.collectAsState()
    val sleepDurationH by viewModel.sleepTimeDurationH.collectAsState()
    val sleepDurationM by viewModel.sleepTimeDurationM.collectAsState()

    //Dato Dormir
    var sleepHour by remember { mutableIntStateOf(storedSleepTime.hour)}
    var sleepMinute by remember { mutableIntStateOf(storedSleepTime.minute) }

    //Dato Alarma
    var alarmHour by remember { mutableIntStateOf(alarmTime.hour) }
    var alarmMinute by remember { mutableIntStateOf(alarmTime.minute) }

    //Dato recordatorio
    var reminderMinutes by remember { mutableStateOf("15") }

    //Para la ventana de tiempo
    var showSleepPicker by remember { mutableStateOf(false) }

    //Para el tiempo personalizado
    val currentSleepTime = storedSleepTime

    //Determinar que ventana de tiempo se utilizará
    var editingTime by remember { mutableStateOf("sleep") } // "sleep" o "alarm"

    LaunchedEffect(alarmTime) {
        alarmHour = alarmTime.hour
        alarmMinute = alarmTime.minute
    }


    if (showSleepPicker) {
        val hourInit = if (editingTime == "sleep") sleepHour
        else alarmHour

        val minuteInit = if (editingTime == "sleep") sleepMinute
        else alarmMinute

        ShowTimePickerDialog(hourInit, minuteInit) { h, m ->
            if (editingTime == "sleep")
            {
                viewModel.upLoadStartTime(ActualTime(h,m))
                showSleepPicker = false
            }

            else {
                viewModel.setAlarmTime(AlarmData(h, m))
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
        Spacer(modifier = Modifier.height(20.dp))

        Text("LA ALARMA ", color = Color.White, fontSize = 24.sp)
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            viewModel.setSleepTimeCurrentDeviceTime()
            showPersonalizedTimeCard = true
            editingTime = "sleep"
            showSleepPicker = true

        }){Text("Personalizar hora de sueño")}

        if(showPersonalizedTimeCard) {

            TimeCard(
                "🛏️ Hora personalizada: ",
                formatTimeAMPM2(currentSleepTime)
            )
            {
                editingTime = "sleep"
                showSleepPicker = true
            }

        }

        TimeCard("⏰ Alarma: ",
            formatTimeAMPM(alarmTime))
        {
            editingTime = "alarm"
            showSleepPicker = true
        }

        Spacer(modifier = Modifier.height(16.dp))

        if(showPersonalizedTimeCard) {
            Text(
                text = "🕒 Duración del descanso eterno: $sleepDurationH h" +
                        "\n$sleepDurationM min",
                color = Color.White,
                fontSize = 18.sp
            )

            viewModel.setDuration(TimeSleep(sleepDurationH,sleepDurationM))

            Spacer(modifier = Modifier.height(16.dp))

        }
        else {viewModel.setDuration(TimeSleep(sleepDurationH,sleepDurationM))}

        OutlinedTextField(
            value = reminderMinutes,
            onValueChange = { reminderMinutes = it },
            label = { Text("Recordar dentro: ") },
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
            val sleepTimeSet =
                if (showPersonalizedTimeCard) storedSleepTime
                else { storedSleepTime }

            onSetAlarmClick(alarmTime, sleepTimeSet, sleepDuration)

        }) {
            Text("Establecer alarma")
        }
    }
}


