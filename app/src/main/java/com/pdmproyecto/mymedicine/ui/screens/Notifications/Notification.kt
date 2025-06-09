package com.pdmproyecto.mymedicine.ui.screens.Notifications

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class Notification(
    val id: Int,
    val title: String,
    val message: String,
    val time: String,
    val dateLabel: String?
) {
    var isRead by mutableStateOf(false)
    var isActive by mutableStateOf(false)
}
