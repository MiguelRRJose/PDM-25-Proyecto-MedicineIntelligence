package com.ayala.monitor_dream.utils

import com.ayala.monitor_dream.navigation.ActualTime
import com.ayala.monitor_dream.navigation.AlarmData
import com.ayala.monitor_dream.navigation.DeviceTime
import com.ayala.monitor_dream.navigation.ReminderTime
import com.ayala.monitor_dream.navigation.TimeSleep
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

fun formatTimeAMPM(alarmData: AlarmData): String {
    val calendar = Calendar.getInstance().apply {
        set(Calendar.HOUR_OF_DAY, alarmData.hour)
        set(Calendar.MINUTE, alarmData.minute)
    }
    val formatter = SimpleDateFormat("hh:mm a", Locale.getDefault())
    return formatter.format(calendar.time)
}

fun formatTimeAMPM2(actualTime: ActualTime): String {
    val calendar = Calendar.getInstance().apply {
        set(Calendar.HOUR_OF_DAY, actualTime.hour)
        set(Calendar.MINUTE, actualTime.minute)
    }
    val formatter = SimpleDateFormat("hh:mm a", Locale.getDefault())
    return formatter.format(calendar.time)
}

fun formatTimeAMPM3(deviceTime: DeviceTime): String {
    val calendar = Calendar.getInstance().apply {
        set(Calendar.HOUR_OF_DAY, deviceTime.hour)
        set(Calendar.MINUTE, deviceTime.minute)
    }
    val formatter = SimpleDateFormat("hh:mm a", Locale.getDefault())
    return formatter.format(calendar.time)
}


fun convertMillisToActualData(millis: Long): DeviceTime {
    val calendar = Calendar.getInstance().apply {
        timeInMillis = millis
    }
    val hour = calendar.get(Calendar.HOUR_OF_DAY)
    val minute = calendar.get(Calendar.MINUTE)
    return DeviceTime(hour, minute)
}



