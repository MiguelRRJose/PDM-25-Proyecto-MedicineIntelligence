package com.ayala.monitor_dream.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ayala.monitor_dream.composables.ShowTimePickerDialog
import com.ayala.monitor_dream.composables.TextDuration
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
    var customButtonTime by remember { mutableStateOf(true) }

    //Datos ViewModel
    val alarmTime by viewModel.alarmTime.collectAsState()
    val storedSleepTime by viewModel.startTime.collectAsState()
    val sleepDuration by viewModel.duration.collectAsState()
    val sleepDurationT by viewModel.sleepTimeDurationH.collectAsState()
    //val sleepDurationM by viewModel.sleepTimeDurationM.collectAsState()

    //Dato recordatorio
    var reminderMinutes by remember { mutableStateOf("15") }

    //Para la ventana de tiempo
    var showSleepPicker by remember { mutableStateOf(false) }

    //Para el tiempo personalizado
    val currentSleepTime = storedSleepTime

    //Determinar que ventana de tiempo se utilizará
    var editingTime by remember { mutableStateOf("sleep") } // "sleep" o "alarm"

    if (showSleepPicker) {
        val hourInit = if (editingTime == "sleep") storedSleepTime.hour
        else alarmTime.hour

        val minuteInit = if (editingTime == "sleep") storedSleepTime.minute
        else alarmTime.minute

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

    Box(modifier = Modifier.fillMaxSize()) {
        Image(painter = painterResource(id = com.ayala.monitor_dream.R.drawable.backgroud_1),
            contentDescription = "Background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
            )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(50.dp))

            Text("Dr. Sueño ", color = Color.White, fontSize = 24.sp)
            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            )
            {
                if (customButtonTime) {

                    Button(
                        onClick = {
                            showPersonalizedTimeCard = true
                            editingTime = "sleep"
                            showSleepPicker = true
                            customButtonTime = false
                        },
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3A5A9A)),
                        modifier = Modifier.weight(1f)
                    ) {

                        Text("Hora personalizada ⏲️")
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = {
                            viewModel.setSleepTimeCurrentDeviceTime()
                            editingTime = "sleep"
                            customButtonTime = false
                        }, shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF5E8BF0)),
                        modifier = Modifier.weight(1f)


                    ) {
                        Text("Hora del dispositivo 📱")
                    }
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                TimeCard(
                    "⏰ Alarma: ",
                    formatTimeAMPM(alarmTime)
                )
                {
                    editingTime = "alarm"
                    showSleepPicker = true
                }

                if (showPersonalizedTimeCard) {

                    TimeCard(
                        "🛏️ Hora: ",
                        formatTimeAMPM2(currentSleepTime)
                    )
                    {
                        editingTime = "sleep"
                        showSleepPicker = true
                    }
                }

            }

            Spacer(modifier = Modifier.height(100.dp))

            //La unica intención es que muestre o no el valor de sleepDurationT

            if (showPersonalizedTimeCard) {

                TextDuration(sleepDurationT.hour, sleepDurationT.minute)

                viewModel.setDuration(TimeSleep(sleepDurationT.hour, sleepDurationT.minute))

            } else {

                TextDuration(sleepDurationT.hour, sleepDurationT.minute)

                viewModel.setDuration(TimeSleep(sleepDurationT.hour, sleepDurationT.minute))

            }

            Spacer(modifier = Modifier.height(16.dp))


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
                    else {
                        storedSleepTime
                    }

                onSetAlarmClick(alarmTime, sleepTimeSet, sleepDuration)

            }) {
                Text("Establecer alarma")
            }
        }

    }

    //End of the line

}


