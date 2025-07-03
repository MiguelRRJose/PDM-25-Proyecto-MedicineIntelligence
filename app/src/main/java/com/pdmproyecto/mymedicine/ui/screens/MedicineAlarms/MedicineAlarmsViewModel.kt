package com.pdmproyecto.mymedicine.ui.screens.MedicineAlarms

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.pdmproyecto.mymedicine.MyMedicineApplication
import com.pdmproyecto.mymedicine.data.models.Medicine
import com.pdmproyecto.mymedicine.data.repositories.medicine.MedicineRepository
import com.pdmproyecto.mymedicine.data.repositories.medicine.MedicineRepositoryInterface
import kotlinx.coroutines.launch
import java.util.Date

class MedicineAlarmsViewModel(private val medicineRepository: MedicineRepositoryInterface): ViewModel() {

    companion object{
        val Factory: ViewModelProvider.Factory = viewModelFactory{
            initializer {
                val application = (this[APPLICATION_KEY] as MyMedicineApplication)
                MedicineAlarmsViewModel(application.appProvider.provideMedicineRepository())
            }
        }
    }

    private val _medicineList = medicineRepository.getMedicinesFromPatientId(1)
    val medicineList = _medicineList

    fun setMedicineToGeneric(): Medicine{
        return Medicine(
            id = -1,
            patientId = -1,
            name = "",
            unit = "",
            amount = 0f,
            startDate = Date(),
            finishDate = Date(),
            timeLap = 0,
            timeLapUnit = ""
        )
    }

    fun removeMedicine(medicine: Medicine){
        viewModelScope.launch {
            medicineRepository.removeMedicine(medicine.id)
        }

    }
}