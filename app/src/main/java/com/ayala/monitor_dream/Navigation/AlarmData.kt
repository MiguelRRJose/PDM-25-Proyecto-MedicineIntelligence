package com.ayala.monitor_dream.navigation

import kotlinx.serialization.Serializable

@Serializable
data class AlarmData(
    val hour: Int,
    val minute: Int
)
@Serializable
data class ActualTime(
    val hour: Int,
    val minute: Int,
)
@Serializable
data class TimeSleep(
    val hour: Int,
    val minute: Int
)
@Serializable
data class ReminderTime(
    val minute: Int,
)


