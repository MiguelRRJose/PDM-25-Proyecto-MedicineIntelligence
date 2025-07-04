package com.pdmproyecto.mymedicine.screens.Settings

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class SettingsViewModel : ViewModel() {

    // Flow para emitir eventos de navegación o acciones
    private val _uiEvent = MutableSharedFlow<SettingsUiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    // Funciones que emiten eventos
    suspend fun onNotificationSettingsClicked() {
        _uiEvent.emit(SettingsUiEvent.GoToNotificationSettings)
    }

    suspend fun onChangePasswordClicked() {
        _uiEvent.emit(SettingsUiEvent.GoToChangePassword)
    }

    suspend fun onLogoutClicked() {
        _uiEvent.emit(SettingsUiEvent.Logout)
    }

    // Eventos posibles
    sealed class SettingsUiEvent {
        object GoToNotificationSettings : SettingsUiEvent()
        object GoToChangePassword : SettingsUiEvent()
        object Logout : SettingsUiEvent()
    }
}
