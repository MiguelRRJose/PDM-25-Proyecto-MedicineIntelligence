package com.ayala.monitor_dream.utils

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

fun parseTimeToCalendar(timeString: String): Calendar {

    val sdf = SimpleDateFormat("hh:mm a", Locale.getDefault())
    val parsedDate = sdf.parse(timeString) ?: Date()

    val wakeUpCalendar = Calendar.getInstance()
    wakeUpCalendar.time = parsedDate

    val now = Calendar.getInstance()
    wakeUpCalendar.set(Calendar.YEAR, now.get(Calendar.YEAR))
    wakeUpCalendar.set(Calendar.MONTH, now.get(Calendar.MONTH))
    wakeUpCalendar.set(Calendar.DAY_OF_MONTH, now.get(Calendar.DAY_OF_MONTH))

    if (wakeUpCalendar.before(now)) {
        wakeUpCalendar.add(Calendar.DAY_OF_MONTH, 1)
    }

    return wakeUpCalendar
}

