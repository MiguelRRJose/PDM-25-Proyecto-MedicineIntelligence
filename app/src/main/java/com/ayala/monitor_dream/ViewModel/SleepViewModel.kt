package com.ayala.monitor_dream.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.ayala.monitor_dream.PruebaMain
import com.ayala.monitor_dream.data.repository.AlarmUserPreferenceRepository
import com.ayala.monitor_dream.navigation.ActualTime
import com.ayala.monitor_dream.navigation.AlarmData
import com.ayala.monitor_dream.navigation.ReminderTime
import com.ayala.monitor_dream.navigation.TimeSleep
import com.ayala.monitor_dream.utils.CalculateDurationTime
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.Calendar

class SleepViewModel(
    private val alarmUserPreferenceRepository: AlarmUserPreferenceRepository
) : ViewModel() {

    //Para manejor de "Alarma"

    private val _alarmTime = MutableStateFlow(AlarmData(5,30))

    val alarmTime: StateFlow<AlarmData> =_alarmTime

    fun setAlarmTime(alarmData: AlarmData) {
        _alarmTime.value = alarmData
        upLoadAlarmUser(alarmData)
    }

    fun upLoadAlarmUser(alarmData: AlarmData) {
        viewModelScope.launch {
            alarmUserPreferenceRepository.saveAlarmUser(alarmData)
        }
    }

    //Manejo de los "Tiempo_Sistema"

    private fun getCurrentDeviceTime(): ActualTime
    {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)
        return ActualTime(hour, minute)
    }

    fun setSleepTimeCurrentDeviceTime()
    {
        _startTime.value = getCurrentDeviceTime()
    }

    private val _startTime = MutableStateFlow<ActualTime>(getCurrentDeviceTime())

    val startTime: StateFlow<ActualTime> = _startTime

    fun setStartTime(actualTime: ActualTime) {

        _startTime.value = actualTime
        upLoadStartTime(actualTime)
    }

    fun upLoadStartTime(actualTime: ActualTime) {
        viewModelScope.launch {
            alarmUserPreferenceRepository.saveStartTime(actualTime)
        }
    }


    //Duracion de sueño

    private val _duration = MutableStateFlow(TimeSleep(0,0))

    val duration: StateFlow<TimeSleep> = _duration

    fun setDuration(timeSleep: TimeSleep) {
        _duration.value = timeSleep
    }

    //Recordatorio para dormir

    private val _reminder = MutableStateFlow(ReminderTime(0))

    val reminder: StateFlow<ReminderTime> = _reminder

    fun setReminder(reminderTime: ReminderTime) {
        _reminder.value = reminderTime
    }

    //Calculadora Tiempo

    val sleepTimeDurationH : StateFlow<TimeSleep> = combine(startTime,alarmTime){ currentSleepTime, currentAlarmTime ->

       val sleepDurationH = CalculateDurationTime.calculateHour(
            currentSleepTime.hour,
            currentSleepTime.minute,
            currentAlarmTime.hour,
            currentAlarmTime.minute
        )

       val sleepDurationM = CalculateDurationTime.calculateMinute (
            currentSleepTime.minute,
            currentAlarmTime.minute
        )

        TimeSleep(sleepDurationH,sleepDurationM)

    }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = TimeSleep(0,0)
            )

    //Funciones de inicialización en general

    init {
        viewModelScope.launch {
            alarmUserPreferenceRepository.startTime
                .collect { initialTimeFromRepository ->
                    if (initialTimeFromRepository != null){
                    _startTime.value  = initialTimeFromRepository
                    }
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
