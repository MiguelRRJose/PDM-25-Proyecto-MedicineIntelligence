package com.pdmproyecto.mymedicine.ui.screens.Sleep

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.pdmproyecto.mymedicine.Composables.ButtonAction
import com.pdmproyecto.mymedicine.Composables.ButtonSleepScreen
import com.pdmproyecto.mymedicine.Composables.PersonalBackground
import com.pdmproyecto.mymedicine.Composables.PersonalSpacer
import com.pdmproyecto.mymedicine.Composables.SelectedImage
import com.pdmproyecto.mymedicine.Composables.ShowReminderPickerDialog
import com.pdmproyecto.mymedicine.Composables.ShowTimePickerDialog
import com.pdmproyecto.mymedicine.Composables.TextSubDatePersColr
import com.pdmproyecto.mymedicine.Composables.TextTittlePersColr
import com.pdmproyecto.mymedicine.Composables.TimeCard
import com.pdmproyecto.mymedicine.R
import com.pdmproyecto.mymedicine.ViewModel.DataViewModel
import com.pdmproyecto.mymedicine.ViewModel.SleepViewModel
import com.pdmproyecto.mymedicine.ui.screens.Navigation.ActualTime
import com.pdmproyecto.mymedicine.ui.screens.Navigation.AlarmData
import com.pdmproyecto.mymedicine.ui.screens.Navigation.ReminderTime
import com.pdmproyecto.mymedicine.ui.screens.Navigation.SleepSummary
import com.pdmproyecto.mymedicine.ui.screens.Navigation.SleepY
import com.pdmproyecto.mymedicine.ui.screens.Navigation.TimeSleep
import com.pdmproyecto.mymedicine.utils.CalculateDurationTime.calculateTotal
import com.pdmproyecto.mymedicine.utils.formatTimeAMPM

@SuppressLint("ScheduleExactAlarm")
@Composable
fun SleepAlarmScreen(

    viewModel: SleepViewModel,
    navController : NavController,
) {

    val dataViewModel: DataViewModel = viewModel()

    var showPersonalizedTimeCard by remember { mutableStateOf(false) }
    var customButtonTime by remember { mutableStateOf(true) }
    var showReminderTime by remember { mutableStateOf(false) }

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

    //Valores del picker

    if (showSleepPicker) {
        val hourInit =
            if (editingTime == "sleep")
                storedSleepTime.hour
            else
                alarmTime.hour

        val minuteInit =
            if (editingTime == "sleep")
                storedSleepTime.minute
            else
                alarmTime.minute

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
            sleepH = storedSleepTime.hour,
            sleepM = storedSleepTime.minute,
            minute = reminderTime.minute,
            onTimeSelected =  { selectedMinute ->
                viewModel.setReminder(ReminderTime(selectedMinute))}
        )
    }

    Box(modifier = Modifier.fillMaxSize()) {

        PersonalBackground(R.drawable.backgroud_1,"background")

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            PersonalSpacer(50)

            TextTittlePersColr("Dr.Sueño", Color.White)

            PersonalSpacer(16)

            TextSubDatePersColr(dateDetails.nameDay,dateDetails.dayOfMonth, dateDetails.month, dateDetails.year,Color.White)

            PersonalSpacer(16)

            SelectedImage(R.drawable.astro_durmiendo, "Astronauta dormido")

            PersonalSpacer(30)

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            )
            {
                if (customButtonTime) {

                    ButtonAction(action = "Hora personalizada", modifier = Modifier.weight(1f))
                    {
                        showPersonalizedTimeCard = true
                        editingTime = "sleep"

                        showSleepPicker = true
                        showReminderTime = true
                        customButtonTime = false
                    }

                    ButtonAction("Hora del dispositivo", modifier = Modifier.weight(1f))
                    {
                        viewModel.setSleepTimeCurrentDeviceTime()
                        editingTime = "sleep"

                        customButtonTime = false
                    }
                }
            }
            PersonalSpacer(30)

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {
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
            PersonalSpacer(40)

            if (showSleepPicker) {

                TimeCard("Meta para dormir ",
                    sleepDuration.hour.toString() + " h : " + sleepDuration.minute.toString() + " min",
                    R.drawable.tick_mark_purple_1) {}

                viewModel.setDuration(TimeSleep(sleepDuration.hour, sleepDuration.minute))

            } else {

                TimeCard("Meta para dormir ",
                    sleepDuration.hour.toString() + " h : " + sleepDuration.minute.toString() + " min",
                    R.drawable.tick_mark_purple_1)
                {

                }

                viewModel.setDuration(TimeSleep(sleepDuration.hour, sleepDuration.minute))
            }

            PersonalSpacer(16)

            if (showReminderTime)
            {
                TimeCard("Recordatorio: ",reminderTime.minute.toString() + " minutos", R.drawable.tick_mark_purple_1)
                {
                    showReminderPicker = true
                }
            }

            PersonalSpacer(24)

            ButtonSleepScreen("Establecer Alarma",10)
            {
                viewModel.setDateDetails(dateDetails)
                navController.navigate("sleep_screen")

                dataViewModel.insertNewData(dateDetails.nameDay, calculateTotal(sleepDuration.hour, sleepDuration.minute))
            }

            ButtonSleepScreen("Visualizar reporte de SleepY",10)
            {
                navController.navigate("sleep_summary")

            }

            ButtonSleepScreen("Retroceso",10)
            {
                navController.popBackStack()

            }
        }
    }
}


