package com.pdmproyecto.mymedicine.ui.screens.Login.Modal

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class PatientRegisterViewModel : ViewModel() {

    var dui by mutableStateOf("")
    var email by mutableStateOf("")
    var age by mutableStateOf("")
    var phone by mutableStateOf("")
    var password by mutableStateOf("")
    var username by mutableStateOf("")
    var showSuccess by mutableStateOf(false)

    fun onRegisterClick() {
        showSuccess = true
    }

    fun dismissSuccessModal() {
        showSuccess = false
    }
}
