package com.ayala.monitor_dream.utils

import com.ayala.monitor_dream.navigation.ActualTime
import com.ayala.monitor_dream.navigation.AlarmData
import com.ayala.monitor_dream.navigation.DeviceTime
import java.text.SimpleDateFormat
import java.util.Calendar
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



