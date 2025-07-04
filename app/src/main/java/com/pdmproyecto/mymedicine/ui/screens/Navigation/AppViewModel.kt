package com.pdmproyecto.mymedicine.ui.screens.Navigation

import android.content.Context
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pdmproyecto.mymedicine.MyMedicineApplication
import com.pdmproyecto.mymedicine.data.repositories.medicine.MedicineRepositoryInterface
import com.pdmproyecto.mymedicine.notify.MedicineNotificationHelper
import kotlinx.coroutines.launch

class AppViewModel(
    private val medicineRepository: MedicineRepositoryInterface
) : ViewModel() {

    fun startAlarm(context: Context) {
        viewModelScope.launch {
            medicineRepository.getAllMedicines().collect {medicineList ->
                MedicineNotificationHelper.programAlarm(context, medicineList)
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val app = (this[APPLICATION_KEY] as MyMedicineApplication)
                AppViewModel(app.appProvider.provideMedicineRepository())
            }
        }
    }

}
