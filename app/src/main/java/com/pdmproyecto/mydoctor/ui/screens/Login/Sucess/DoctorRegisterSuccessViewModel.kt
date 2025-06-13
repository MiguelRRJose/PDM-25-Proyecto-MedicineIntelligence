package com.pdmproyecto.mydoctor.ui.screens.Login.Sucess

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class DoctorRegisterSuccessViewModel : ViewModel() {

    var username by mutableStateOf("")

    fun updateUsername(value: String) {
        username = value
    }

    fun resetUsername() {
        username = ""
    }
}
