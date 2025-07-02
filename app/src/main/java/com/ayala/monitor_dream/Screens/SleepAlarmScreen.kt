package com.ayala.monitor_dream.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ayala.monitor_dream.R
import com.ayala.monitor_dream.composables.ButtonAction
import com.ayala.monitor_dream.composables.PersonalBackground
import com.ayala.monitor_dream.composables.SelectedImage
import com.ayala.monitor_dream.composables.ShowReminderPickerDialog
import com.ayala.monitor_dream.composables.ShowTimePickerDialog
import com.ayala.monitor_dream.composables.TextSubDate
import com.ayala.monitor_dream.composables.TextTittle
import com.ayala.monitor_dream.composables.TimeCard
import com.ayala.monitor_dream.navigation.ActualTime
import com.ayala.monitor_dream.navigation.AlarmData
import com.ayala.monitor_dream.navigation.ReminderTime
import com.ayala.monitor_dream.navigation.SleepY
import com.ayala.monitor_dream.navigation.TimeSleep
import com.ayala.monitor_dream.utils.formatTimeAMPM
import com.ayala.monitor_dream.viewModel.SleepViewModel


@Composable
fun SleepAlarmScreen(
    viewModel: SleepViewModel,
    navController : NavController,
) {

    var showPersonalizedTimeCard by remember { mutableStateOf(false) }
    var customButtonTime by remember { mutableStateOf(true) }

    //Datos ViewModel
    val alarmTime by viewModel.alarmTime.collectAsState()
    val storedSleepTime by viewModel.startTime.collectAsState()
    val sleepDuration by viewModel.sleepTimeDurationH.collectAsState()
    val reminderTime by viewModel.reminder.collectAsState()
    val dateDetails by viewModel.dateDetails.collectAsState()


    //Para la ventana de tiempo
    var showSleepPicker by remember { mutableStateOf(false) }
    var showReminderPicker by remember { mutableStateOf(false) }

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
                viewModel.setStartTime(ActualTime(h,m))
                showSleepPicker = false
            }

            else {
                viewModel.setAlarmTime(AlarmData(h, m))
            }
            showSleepPicker = false
        }
    }

    if (showReminderPicker)
    {
        ShowReminderPickerDialog (
            minute = reminderTime.minute, onTimeSelected =  { selectedMinute ->
                viewModel.setReminder(ReminderTime(selectedMinute))}
        )
    }

    Box(modifier = Modifier.fillMaxSize()) {

        PersonalBackground(R.drawable.backgroud_1,"background")

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(50.dp))

            TextTittle("Dr. Sueño")

            Spacer(modifier = Modifier.height(16.dp))

            TextSubDate(dateDetails.dayOfMonth, dateDetails.month, dateDetails.year)

            Spacer(modifier = Modifier.height(16.dp))

            SelectedImage(R.drawable.astro_durmiendo, "Astronauta dormido")

            Spacer(modifier = Modifier.height(30.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            )
            {
                if (customButtonTime) {

                    ButtonAction(action = "Hora personalizada", modifier = Modifier.weight(1f),
                        onClick = {
                            showPersonalizedTimeCard = true
                            editingTime = "sleep"
                            showSleepPicker = true
                            customButtonTime = false})

                    ButtonAction("Hora del dispositivo", modifier = Modifier.weight(1f),
                        onClick = {
                            viewModel.setSleepTimeCurrentDeviceTime()
                            editingTime = "sleep"
                            customButtonTime = false})
                }
            }

            Spacer(modifier = Modifier.height(30.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {

                //

                TimeCard("Alarma: ", formatTimeAMPM(alarmTime.hour, alarmTime.minute) , R.drawable.bell_white_1)
                {
                    editingTime = "alarm"
                    showSleepPicker = true
                }

                if (showPersonalizedTimeCard) {

                    TimeCard("Hora: ", formatTimeAMPM(currentSleepTime.hour, currentSleepTime.minute), R.drawable.bed_white_1)
                    {
                        editingTime = "sleep"
                        showSleepPicker = true
                    }
                }
            }

            Spacer(modifier = Modifier.height(40.dp))

            if (showPersonalizedTimeCard) {

                TimeCard("Meta para dormir ",
                    sleepDuration.hour.toString() + " h : " + sleepDuration.minute.toString() + " min",
                    R.drawable.tick_mark_purple_1) {}

                viewModel.setDuration(TimeSleep(sleepDuration.hour, sleepDuration.minute))

            } else {

                TimeCard("Meta para dormir ",
                    sleepDuration.hour.toString() + " h : " + sleepDuration.minute.toString() + " min",
                    R.drawable.tick_mark_purple_1) {}

                viewModel.setDuration(TimeSleep(sleepDuration.hour, sleepDuration.minute))
            }

            Spacer(modifier = Modifier.height(16.dp))

            TimeCard("Recordatorio: ",reminderTime.minute.toString() + " minutos", R.drawable.tick_mark_purple_1)
            {
                showReminderPicker = true
            }

            Spacer(modifier = Modifier.height(24.dp))

            ButtonAction("Establecer alarma",
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    val sleepTimeSet = if (showPersonalizedTimeCard) storedSleepTime
                    else {
                        storedSleepTime
                    }

                    viewModel.setAlarmTime(alarmTime)

                    viewModel.setStartTime(sleepTimeSet)

                    viewModel.setDateDetails(dateDetails)

                    navController.navigate(SleepY)
                }
            )
        }
    }
}


