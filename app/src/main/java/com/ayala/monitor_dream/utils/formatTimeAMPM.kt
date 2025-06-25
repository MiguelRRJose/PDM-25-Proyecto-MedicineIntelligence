package com.ayala.monitor_dream.utils

import com.ayala.monitor_dream.model.AlarmData
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

fun getCurrentFormattedTime(): String {
    val sdf = SimpleDateFormat("hh:mm a", Locale.getDefault())
    return sdf.format(Date())
}
