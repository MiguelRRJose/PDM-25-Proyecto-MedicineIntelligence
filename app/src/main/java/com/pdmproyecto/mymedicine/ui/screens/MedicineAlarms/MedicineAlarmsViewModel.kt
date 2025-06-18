package com.pdmproyecto.mymedicine.ui.screens.MedicineAlarms

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.pdmproyecto.mymedicine.data.Medicine
import com.pdmproyecto.mymedicine.data.dummyMedicines

class MedicineAlarmsViewModel: ViewModel() {

    private val _medicineList = mutableStateListOf<Medicine>().apply{addAll(dummyMedicines)}
    val medicineList = _medicineList

    fun addMedicine(medicine: Medicine){
        _medicineList.add(medicine)
    }

    fun removeMedicine(medicine: Medicine){
        _medicineList.remove(medicine)
    }
}