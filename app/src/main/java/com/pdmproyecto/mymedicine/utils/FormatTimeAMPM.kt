package com.pdmproyecto.mymedicine.utils

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

fun formatTimeAMPM(hour: Int , minute: Int): String {
    val calendar = Calendar.getInstance().apply {
        set(Calendar.HOUR_OF_DAY, hour)
        set(Calendar.MINUTE, minute)
    }
    val formatter = SimpleDateFormat("hh:mm a", Locale.getDefault())
    return formatter.format(calendar.time)
}



