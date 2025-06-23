package com.ayala.monitor_dream.ViewModel


import androidx.lifecycle.ViewModel
import com.ayala.monitor_dream.utils.formatTimeAMPM
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SleepViewModel : ViewModel() {

    private val _alarmTime = MutableStateFlow("06:00 AM")
    val alarmTime: StateFlow<String> = _alarmTime


    fun setAlarmTime(hour: Int, minute: Int) {
        val formatted = formatTimeAMPM(hour, minute)
        _alarmTime.value = formatted
    }

}
