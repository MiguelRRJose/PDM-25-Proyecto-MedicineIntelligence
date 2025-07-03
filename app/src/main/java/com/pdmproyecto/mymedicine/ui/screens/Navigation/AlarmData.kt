package com.pdmproyecto.mymedicine.ui.screens.Navigation

import kotlinx.serialization.Serializable

@Serializable
data class AlarmData(
    val hour: Int,
    val minute: Int
)

@Serializable
//Tiempo o del sistema o elegido por el usuario
data class ActualTime(
    val hour: Int,
    val minute: Int
)

@Serializable
//Duracion del sueño
data class TimeSleep(
    val hour: Int,
    val minute: Int
) {
    fun toTotalSeconds(): Int {
        return hour * 3600 + minute * 60
    }
}

@Serializable
//Tiempo interno del sistema
data class DeviceTime(
    val hour: Int,
    val minute: Int
)

@Serializable
// Tiempo de recordatorio
data class ReminderTime(
    val minute: Int,
)

@Serializable
data class DateDetails(
    val year: Int,
    val month: Int,
    val dayOfMonth: Int,
    val nameDay: String
)

