package com.pdmproyecto.mymedicine.ui.screens.MedicineAlarms

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.pdmproyecto.mymedicine.data.models.Medicine
import java.util.Date

class MedicineAlarmsViewModel: ViewModel() {

    private val _medicineList = mutableStateListOf<Medicine>()
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

    fun addMedicine(medicine: Medicine){
        _medicineList.add(medicine)
    }

    fun removeMedicine(medicine: Medicine){
        _medicineList.remove(medicine)
    }
}