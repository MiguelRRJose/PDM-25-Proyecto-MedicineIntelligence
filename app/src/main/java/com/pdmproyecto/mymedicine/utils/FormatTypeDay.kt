package com.pdmproyecto.mymedicine.utils

import java.text.SimpleDateFormat
import java.util.Locale


fun getDayNameFromCalendar(calendar: java.util.Calendar , locale: Locale = Locale.getDefault()): String {
    val dayFormat = SimpleDateFormat("EEEE", locale)
    return dayFormat.format(calendar.time).replaceFirstChar { if (it.isLowerCase()) it.titlecase(locale) else it.toString() }

}