package com.pdmproyecto.mymedicine.screens.Settings.ChangePassword

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.SharedFlow

class ChangePasswordViewModel : ViewModel() {

    private val _uiEvent = MutableSharedFlow<UiEvent>()
    val uiEvent: SharedFlow<UiEvent> = _uiEvent.asSharedFlow()

    private val _currentPassword = MutableStateFlow("")
    val currentPassword: StateFlow<String> = _currentPassword

    private val _newPassword = MutableStateFlow("")
    val newPassword: StateFlow<String> = _newPassword

    private val _confirmPassword = MutableStateFlow("")
    val confirmPassword: StateFlow<String> = _confirmPassword

    fun onCurrentPasswordChanged(value: String) {
        _currentPassword.value = value
    }

    fun onNewPasswordChanged(value: String) {
        _newPassword.value = value
    }

    fun onConfirmPasswordChanged(value: String) {
        _confirmPassword.value = value
    }

    suspend fun onChangePasswordClicked() {
        if (_newPassword.value != _confirmPassword.value) {
            _uiEvent.emit(UiEvent.ShowError("Las contraseñas no coinciden"))
            return
        }

        if (_currentPassword.value.isBlank() || _newPassword.value.isBlank()) {
            _uiEvent.emit(UiEvent.ShowError("Completa todos los campos"))
            return
        }

        // Aquí iría lógica real de cambio de contraseña (ej: API call)
        _uiEvent.emit(UiEvent.PasswordChanged)
    }

    sealed class UiEvent {
        object PasswordChanged : UiEvent()
        data class ShowError(val message: String) : UiEvent()
    }
}
