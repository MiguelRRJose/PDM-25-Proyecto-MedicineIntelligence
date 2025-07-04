package com.pdmproyecto.mymedicine.ui.screens.Login.LoginPrincipal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pdmproyecto.mymedicine.data.models.User
import com.pdmproyecto.mymedicine.data.repositories.user.UserRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginViewModel(
    private val userRepository: UserRepository
) : ViewModel() {


    var isLoading = false
    var loginSuccess = false
    var loginError: String? = null

    var loggedInUser: User? = null


    fun login(email: String, password: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            isLoading = true
            loginError = null

            if (email.isBlank() || password.isBlank()) {
                loginError = "Llene todos los campos"
                onResult(false)
                isLoading = false
                return@launch
            }

            val user = userRepository.getUserByEmail(email)

            if (user != null && user.password == password) {
                loggedInUser = user
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
