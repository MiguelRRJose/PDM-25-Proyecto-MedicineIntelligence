package com.ayala.monitor_dream.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.ayala.monitor_dream.PruebaMain
import com.ayala.monitor_dream.data.repository.AlarmUserPreferenceRepository
import com.ayala.monitor_dream.model.AlarmData
import com.ayala.monitor_dream.utils.formatTimeAMPM
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class SleepViewModel(
    private val alarmUserPreferenceRepository: AlarmUserPreferenceRepository
) : ViewModel() {

    private val _alarmTime = MutableStateFlow(AlarmData(6,0))

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

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val app = this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as PruebaMain
                SleepViewModel(app.alarmUserPreferenceRepository)
            }
        }
    }
}
