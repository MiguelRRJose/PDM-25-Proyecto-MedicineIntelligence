package com.ayala.monitor_dream.ViewModel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class CurrentTimeViewModel : ViewModel() {

    //Futuro viewModel aparte

    private val _startTime = MutableStateFlow<Long?>(null)

    val startTime: StateFlow<Long?> = _startTime

    fun setStartTime(millis: Long) {
        _startTime.value = millis
    }

    fun formatTime(millis: Long): String {
        val sdf = SimpleDateFormat("hh:mm a", Locale.getDefault())
        return sdf.format(Date(millis))
    }

}