package com.pdmproyecto.mydoctor.ui.screens.DoctorDashboard

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class DoctorDashboardViewModel : ViewModel() {

    var doctorName by mutableStateOf("Dr. Oswaldo")
    var totalPacientes by mutableStateOf(24)
    var enTratamiento by mutableStateOf(10)
    var medicamentoFrecuente by mutableStateOf("Omeprazol")
    var promedioSueno by mutableStateOf("7,4 H")
    var calidadSueno by mutableStateOf(87)

    data class Paciente(
        val nombre: String,
        val problema: String,
        val hora: String,
        val imagenId: Int
    )

    var pacientesDelDia by mutableStateOf(
        listOf(
            Paciente("Natasha Romanoff", "Dolor de cabeza", "11:00 AM", com.pdmproyecto.mydoctor.R.drawable.natasha),
            Paciente("Monica Rambeau", "Colesterol", "12:00 AM", com.pdmproyecto.mydoctor.R.drawable.monica)
        )
    )
}
