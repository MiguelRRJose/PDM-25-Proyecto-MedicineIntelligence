package com.pdmproyecto.mymedicine.ui.screens.Notifications

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class NotificationViewModel : ViewModel() {
    var notifications = mutableStateListOf(
        Notification(1, "Resultado De Laboratorio", "Lorem ipsum dolor sit amet...", "2 M", "Ahora"),
        Notification(2, "Próxima Cita", "Lorem ipsum dolor sit amet...", "2 H", "Ahora"),
        Notification(3, "Nuevas Notas Médicas", "Lorem ipsum dolor sit amet...", "3 H", "Ahora"),
        Notification(4, "Cita Agendada", "Lorem ipsum dolor sit amet...", "1 D", "Ayer"),
        Notification(5, "Actualización Del Historial Médico", "Lorem ipsum dolor sit amet...", "5 D", "15 Abril")

    )

    fun markAllAsRead() {
        notifications.forEach { it.isRead = true }
    }

    fun activateNotification(id: Int) {
        notifications.forEach {
            it.isActive = it.id == id
        }
    }
}
