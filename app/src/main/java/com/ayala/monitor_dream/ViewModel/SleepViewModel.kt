package com.ayala.monitor_dream.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.ayala.monitor_dream.PruebaMain
import com.ayala.monitor_dream.data.repository.AlarmUserPreferenceRepository
import com.ayala.monitor_dream.model.ActualTime
import com.ayala.monitor_dream.model.AlarmData
import com.ayala.monitor_dream.utils.formatTimeAMPM
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class SleepViewModel(
    private val alarmUserPreferenceRepository: AlarmUserPreferenceRepository
) : ViewModel() {

    private val _alarmTime = MutableStateFlow(AlarmData(5,30))

    val alarmTime: StateFlow<AlarmData> =_alarmTime

    fun setAlarmTime(alarmData: AlarmData) {
        _alarmTime.value = alarmData
        val formatted = formatTimeAMPM(alarmData)
        saveAlarmUser(formatted)
    }

    fun saveAlarmUser(value: String) {
        viewModelScope.launch {
            alarmUserPreferenceRepository.saveAlarmUser(value)
        }
    }

    val alarmUser: StateFlow<String> = alarmUserPreferenceRepository.alarmUser
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), "06:00 AM")


    //Adicional para el manejo de tiempo dentro del sistema


    private val _startTime = MutableStateFlow<ActualTime?>(null)

    val startTime: StateFlow<ActualTime?> = _startTime

    fun setStartTime(actualTime: ActualTime) {

        _startTime.value = actualTime
        saveStartTime(actualTime)
    }

    fun formatTime(millis: Long): String {
        val sdf = java.text.SimpleDateFormat("hh:mm a", java.util.Locale.getDefault())
        return sdf.format(java.util.Date(millis))
    }

    fun saveStartTime(time: ActualTime) {
        viewModelScope.launch {
            alarmUserPreferenceRepository.saveStartTime(time)
        }
    }

    init {
        viewModelScope.launch {
            alarmUserPreferenceRepository.startTime
                .collect { time ->
                    _startTime.value = time
                }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val app = this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as PruebaMain
                SleepViewModel(app.alarmUserPreferenceRepository)
            }
        }
    }

}
