package com.pdmproyecto.mymedicine.ui.screens.Login.LoginPrincipal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {


    var isLoading = false
    var loginSuccess = false
    var loginError: String? = null


    fun login(email: String, password: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            isLoading = true
            loginError = null

            delay(1000)

            if (email == "usuario@demo.com" && password == "1234") {
                loginSuccess = true
                onResult(true)
            } else {
                loginError = "Credenciales inválidas"
                onResult(false)
            }

            isLoading = false
        }
    }
}
