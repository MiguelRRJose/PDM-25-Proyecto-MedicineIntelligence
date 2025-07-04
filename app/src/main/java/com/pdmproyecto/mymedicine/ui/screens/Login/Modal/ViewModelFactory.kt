// PatientRegisterViewModelFactory.kt
package com.pdmproyecto.mymedicine.ui.screens.Login.Modal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pdmproyecto.mymedicine.data.repositories.user.UserRepository

class PatientRegisterViewModelFactory(
    private val userRepository: UserRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PatientRegisterViewModel(userRepository) as T
    }
}
