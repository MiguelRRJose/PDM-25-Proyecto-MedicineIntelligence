package com.pdmproyecto.mymedicine.ui.screens.PatientDashboard

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class PatientDashboardViewModel : ViewModel() {

    // Nombre del usuario
    var username by mutableStateOf("Nestor")
        private set

    // Clima y motivación
    var weatherMessage by mutableStateOf("Ideal para caminar")
        private set

    var motivationalMessage by mutableStateOf("¡Buena suerte!")
        private set

    // Agua
    var waterIntake by mutableStateOf(4) // vasos tomados
        private set

    fun addWaterIntake() {
        if (waterIntake < 8) waterIntake++
    }

    fun resetWaterIntake() {
        waterIntake = 0
    }

    // Sueño
    var sleepHours by mutableStateOf(7.5f)
        private set

    var sleepQuality by mutableStateOf("Buena")
        private set

    fun updateSleep(hours: Float, quality: String) {
        sleepHours = hours
        sleepQuality = quality
    }

    // Pasos
    var steps by mutableStateOf(5768)
        private set

    fun updateSteps(count: Int) {
        steps = count
    }

    // Temporizador de recordatorio
    var medicineReminderTime by mutableStateOf("00:14:34")
        private set

    fun updateMedicineReminder(time: String) {
        medicineReminderTime = time
    }


    // Peso
    var weight by mutableStateOf(82.5f)
        private set

    var weightGoalPercent by mutableStateOf(80)
        private set

    fun updateWeight(value: Float, percent: Int) {
        weight = value
        weightGoalPercent = percent
    }
}