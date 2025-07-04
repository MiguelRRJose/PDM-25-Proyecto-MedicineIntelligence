package com.pdmproyecto.mymedicine.ui.screens.Login.LoginPrincipal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pdmproyecto.mymedicine.data.repositories.user.UserRepository

class LoginViewModelFactory(
    private val userRepository: UserRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LoginViewModel(userRepository) as T
    }
}
