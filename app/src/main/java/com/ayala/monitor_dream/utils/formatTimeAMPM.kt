package com.ayala.monitor_dream.utils

import com.ayala.monitor_dream.navigation.ActualTime
import com.ayala.monitor_dream.navigation.AlarmData
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

fun convertMillisToActualData(millis: Long): ActualTime {
    val calendar = Calendar.getInstance().apply {
        timeInMillis = millis
    }
    val hour = calendar.get(Calendar.HOUR_OF_DAY)
    val minute = calendar.get(Calendar.MINUTE)
    return ActualTime(hour, minute)
}


fun getCurrentFormattedTime(): String {
    val sdf = SimpleDateFormat("hh:mm a", Locale.getDefault())
    return sdf.format(Date())
}
