package com.ayala.monitor_dream.composables

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.TimePickerDialog
import android.util.Log
import androidx.annotation.RequiresPermission
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ayala.monitor_dream.notify.NotificationHelper
import com.ayala.monitor_dream.utils.requestNotificationPermissionIfNeeded
import com.ayala.monitor_dream.viewModel.SleepViewModel
import java.util.Calendar

@Composable
fun ShowTimePickerDialog(
    hour: Int,
    minute: Int,
    onTimeSelected: (Int, Int) -> Unit
) {
    val context = LocalContext.current

    val timePickerDialog = remember {
        TimePickerDialog(
            context, { _, selectedHour, selectedMinute ->
                onTimeSelected(selectedHour, selectedMinute) }, hour, minute, false
        )
    }

    LaunchedEffect(Unit) {
        timePickerDialog.show()
    }
}

@SuppressLint("ScheduleExactAlarm")
@RequiresPermission(value = Manifest.permission.SCHEDULE_EXACT_ALARM, conditional = true)
@Composable
fun ShowReminderPickerDialog(
    sleepH:Int,
    sleepM:Int,
    minute: Int,
    onTimeSelected: (Int) -> Unit
) {
    val context = LocalContext.current
    val activity = context as? Activity

    val currentHour = remember { Calendar.getInstance().get(Calendar.HOUR_OF_DAY) }

    val timePickerDialog = remember {
        TimePickerDialog(
            context,
            { _, _, selectedMinute ->
                onTimeSelected(selectedMinute)

                if (activity != null && requestNotificationPermissionIfNeeded(activity)) {
                    NotificationHelper.createNotificationChannel(context)
                    NotificationHelper.scheduleNotificationAtTime(context,selectedMinute,sleepH,sleepM)
                }

            },
            currentHour,
            minute,
            false
        )
    }

    LaunchedEffect(Unit) {
        timePickerDialog.show()
    }
}
