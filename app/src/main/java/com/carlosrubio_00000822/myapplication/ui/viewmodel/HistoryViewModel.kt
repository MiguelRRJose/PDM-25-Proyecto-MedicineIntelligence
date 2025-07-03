package com.carlosrubio_00000822.myapplication.ui.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class MedicineHistory(
    val id         : Long,
    val name       : String,
    val dosage     : String,
    val dateTime   : String,
    val frequency  : String
)

class HistoryViewModel : ViewModel() {

    private val _historyList = MutableStateFlow<List<MedicineHistory>>(listOf(
        MedicineHistory(1, "Paracetamol", "500 mg", "12 abr 2025 – 08:00 AM", "Cada 6 horas"),
        MedicineHistory(2, "Ibuprofeno",  "400 mg", "11 abr 2025 – 09:15 AM", "Cada 8 horas"),
        // …etc
    ))
    val historyList: StateFlow<List<MedicineHistory>> = _historyList

    fun deleteItem(item: MedicineHistory) {
        _historyList.value = _historyList.value.filter { it.id != item.id }
    }
}
