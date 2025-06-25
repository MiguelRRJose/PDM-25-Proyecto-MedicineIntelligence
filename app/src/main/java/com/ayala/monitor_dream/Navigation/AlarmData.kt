// AlarmData.kt
package com.ayala.monitor_dream.model

import kotlinx.serialization.Serializable

@Serializable
data class AlarmData(
    val hour: Int,
    val minute: Int
)


