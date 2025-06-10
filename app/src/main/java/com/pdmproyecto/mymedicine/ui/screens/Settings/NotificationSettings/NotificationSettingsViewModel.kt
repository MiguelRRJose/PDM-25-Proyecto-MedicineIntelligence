package com.pdmproyecto.mymedicine.ui.screens.Settings.NotificationSettings

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class NotificationSettingsViewModel : ViewModel() {

    // Estados de los switches
    private val _generalNotifications = MutableStateFlow(true)
    val generalNotifications: StateFlow<Boolean> = _generalNotifications

    private val _soundNotifications = MutableStateFlow(true)
    val soundNotifications: StateFlow<Boolean> = _soundNotifications

    private val _appointmentReminders = MutableStateFlow(false)
    val appointmentReminders: StateFlow<Boolean> = _appointmentReminders

    private val _medicalUpdates = MutableStateFlow(true)
    val medicalUpdates: StateFlow<Boolean> = _medicalUpdates

    // Funciones para actualizar valores
    fun onGeneralNotificationsChanged(value: Boolean) {
        _generalNotifications.value = value
    }

    fun onSoundNotificationsChanged(value: Boolean) {
        _soundNotifications.value = value
    }

    fun onAppointmentRemindersChanged(value: Boolean) {
        _appointmentReminders.value = value
    }

    fun onMedicalUpdatesChanged(value: Boolean) {
        _medicalUpdates.value = value
    }
}
