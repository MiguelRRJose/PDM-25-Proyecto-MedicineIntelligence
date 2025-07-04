package com.pdmproyecto.mymedicine.ui.screens.Login.Sucess

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class PatientRegisterSuccessViewModel : ViewModel() {

    var username by mutableStateOf("")

    fun updateUsername(value: String) {
        username = value
    }

    fun resetUsername() {
        username = ""
    }
}
