package com.pdmproyecto.mymedicine.ui.screens.Login.Modal

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pdmproyecto.mymedicine.data.models.User
import com.pdmproyecto.mymedicine.data.models.toDatabase
import com.pdmproyecto.mymedicine.data.repositories.user.UserRepository
import kotlinx.coroutines.launch

private fun validateDUI(dui: String): Boolean {
    return dui.length == 9 && dui.all { it.isDigit() } && dui.startsWith("0")
}

private fun validateEmail(email: String): Boolean {
    return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
}

private fun validateAge(age: String): Boolean {
    return age.toIntOrNull()?.let { it >= 0 } == true
}

private fun validatePhone(phone: String): Boolean {
    return phone.length == 8 && phone.all { it.isDigit() }
}


class PatientRegisterViewModel(
    private val userRepository: UserRepository
) : ViewModel() {

    var dui by mutableStateOf("")
    var email by mutableStateOf("")
    var age by mutableStateOf("")
    var phone by mutableStateOf("")
    var password by mutableStateOf("")
    var username by mutableStateOf("")
    var showSuccess by mutableStateOf(false)

    fun onRegisterClick() {
        if (
            validateDUI(dui) &&
            validateEmail(email) &&
            validateAge(age) &&
            validatePhone(phone) &&
            password.isNotBlank() &&
            username.isNotBlank()
        ) {
            viewModelScope.launch {
                try {
                    val user = User(
                        id = 0,
                        name = username,
                        email = email,
                        phoneNumber = phone,
                        dui = dui,
                        age = age.toIntOrNull() ?: -1,
                        password = password
                    )

                    if (user.age < 0) {
                        println("Edad inválida")
                        return@launch
                    }

                    val existingByEmail = userRepository.getUserByEmail(email)
                    val existingByDui = userRepository.getUserByDui(dui)

                    if (existingByEmail == null && existingByDui == null) {
                        userRepository.addUser(user)
                        showSuccess = true
                    } else {
                        println("Usuario ya existente.")
                    }

                } catch (e: Exception) {
                    println("Error en el registro: ${e.message}")
                }
            }
        }
    }




    fun dismissSuccessModal() {
        showSuccess = false
    }
}
