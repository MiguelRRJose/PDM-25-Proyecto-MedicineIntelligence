package com.ayala.monitor_dream.ViewModel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.time.LocalTime

class SleepViewModel : ViewModel() {

    @RequiresApi(Build.VERSION_CODES.O)
    private val _alarmTime = MutableStateFlow<LocalTime>(LocalTime.of(6, 0))

    @RequiresApi(Build.VERSION_CODES.O)
    val alarmTime: StateFlow<LocalTime> = _alarmTime

    @RequiresApi(Build.VERSION_CODES.O)
    fun setAlarmTime(time: LocalTime) {
        _alarmTime.value = time
    }

}
