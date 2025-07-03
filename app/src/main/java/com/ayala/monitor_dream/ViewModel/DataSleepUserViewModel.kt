package com.ayala.monitor_dream.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.ayala.monitor_dream.dataBase.AppDatabase
import com.ayala.monitor_dream.data.UserSleepData
import com.ayala.monitor_dream.data.alarmDAO.DataDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class DataViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val dataDao: DataDao

    init {
        Log.d("DataViewModel" , "Inicializando DataViewModel...")
        try {
            Log.d("DataViewModel" , "Intentando obtener la base de datos...")
            val database = AppDatabase.getDatabase(application)
            Log.d("DataViewModel" , "Base de datos obtenida: ${database != null}")
            dataDao = database.dataDao()
            Log.d("DataViewModel" , "DataDao obtenido: ${dataDao != null}")
        } catch (e: Exception) {
            Log.e("DataViewModel" , "Error durante la inicialización del ViewModel" , e)
            throw e
        }
        Log.d("DataViewModel" , "DataViewModel inicializado correctamente.")
    }

    val allDataItems: Flow<List<UserSleepData>> = dataDao.getAllData()

    val totalItemCount: StateFlow<Int> = dataDao.getTotalItemCount()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = 0
        )

    fun clearAllData() {
        viewModelScope.launch(Dispatchers.IO) {
            dataDao.deleteAllData()
        }
    }

    fun insertNewData(label: String , value: Double) {
        viewModelScope.launch(Dispatchers.IO) { // Ejecutar en un hilo de fondo
            val newData = UserSleepData(label = label , value = value)
            dataDao.insertData(newData)
        }

    }


}

