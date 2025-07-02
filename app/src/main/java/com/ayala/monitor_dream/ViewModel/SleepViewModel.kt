package com.ayala.monitor_dream.viewModel

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.ayala.monitor_dream.PruebaMain
import com.ayala.monitor_dream.composables.ShowTimePickerDialog
import com.ayala.monitor_dream.data.repository.AlarmUserPreferenceRepository
import com.ayala.monitor_dream.navigation.ActualTime
import com.ayala.monitor_dream.navigation.AlarmData
import com.ayala.monitor_dream.navigation.DateDetails
import com.ayala.monitor_dream.navigation.DeviceTime
import com.ayala.monitor_dream.navigation.ReminderTime
import com.ayala.monitor_dream.navigation.TimeSleep
import com.ayala.monitor_dream.utils.CalculateDurationTime
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import java.util.Calendar

class SleepViewModel(
    private val alarmUserPreferenceRepository: AlarmUserPreferenceRepository
) : ViewModel() {

    //Manejo de la alarma

    private val _alarmTime = MutableStateFlow(AlarmData(5,30))

    val alarmTime: StateFlow<AlarmData> =_alarmTime

    //Manejo de los "Tiempo_Sistema"

    private fun getCurrentDeviceTime(): ActualTime
    {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)
        return ActualTime(hour, minute)
    }

    private fun getCurrentDeviceTime2(): DeviceTime
    {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)
        return DeviceTime(hour, minute)
    }

    private fun getCurrentDateDetails(): DateDetails
    {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH) + 1
        val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
        return DateDetails(year, month, dayOfMonth)
    }


    private val _startTime = MutableStateFlow<ActualTime>(getCurrentDeviceTime())

    val startTime: StateFlow<ActualTime> = _startTime


    //Duracion de sueño

    private val _duration = MutableStateFlow(TimeSleep(0,0))

    val duration: StateFlow<TimeSleep> = _duration

    //Tiempo Actual Dispositivo (Problemas con las compatibilidades del sistema)

    private val deviceTime = MutableStateFlow(DeviceTime(getCurrentDeviceTime2().hour,getCurrentDeviceTime2().minute))

    val deviceTimeFlow: StateFlow<DeviceTime> = deviceTime

    //Fecha actual

    private val _dateDetails = MutableStateFlow(getCurrentDateDetails())

    val dateDetails: StateFlow<DateDetails> = _dateDetails

    //Recordatorio para dormir

    private val _reminder = MutableStateFlow(ReminderTime(0))

    val reminder: StateFlow<ReminderTime> = _reminder


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

    init {
        viewModelScope.launch {
            alarmUserPreferenceRepository.alarmUser
                .collect { initialAlarmFromRepository ->
                    if (initialAlarmFromRepository != null){
                        val alarmData = Json.decodeFromString<AlarmData>(initialAlarmFromRepository)
                        _alarmTime.value = alarmData
                    }
                }
        }
    }


    //Métodos de Seteo de datos con "Upload"

    fun setAlarmTime(alarmData: AlarmData) {
        _alarmTime.value = alarmData
        upLoadAlarmUser(alarmData)
    }

    fun setStartTime(actualTime: ActualTime) {

        _startTime.value = actualTime
        upLoadStartTime(actualTime)
    }

    //Conseguir datos dentro del sistema

    fun setSleepTimeCurrentDeviceTime()
    {
        _startTime.value = getCurrentDeviceTime()
    }

    fun setDeviceTime() {
        this.deviceTime.value = getCurrentDeviceTime2()
    }

    //Métodos de seteo de datos basicos

    fun setDuration(timeSleep: TimeSleep) {
        _duration.value = timeSleep
    }

    fun setReminder(reminderTime: ReminderTime) {
        _reminder.value = reminderTime
    }

    fun setDateDetails(dateDetails: DateDetails)
    {
        _dateDetails.value = dateDetails
    }

    //Métodos de subida de datos al DataStore

    fun upLoadStartTime(actualTime: ActualTime) {
        viewModelScope.launch {
            alarmUserPreferenceRepository.saveStartTime(actualTime)
        }
    }

    fun upLoadAlarmUser(alarmData: AlarmData) {
        viewModelScope.launch {
            alarmUserPreferenceRepository.saveAlarmUser(alarmData)
        }
    }

    //Almacenamiento con factory

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val app = this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as PruebaMain
                SleepViewModel(app.alarmUserPreferenceRepository)
            }
        }
    }
}
