package com.pdmproyecto.mymedicine.ui.screens.WaterIntake

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class WaterIntakeViewModel : ViewModel() {

    private val _drank = MutableStateFlow(0)
    private val _goal = 8
    private val _percentage = MutableStateFlow(0f)
    private val _cyclesCompleted = MutableStateFlow(0)

    val drank = _drank.asStateFlow()
    val percentage = _percentage.asStateFlow()
    val cyclesCompleted = _cyclesCompleted.asStateFlow()

    fun onDrink() {
        _drank.value += 1

        if (_drank.value >= _goal) {
            _drank.value = 0
            _cyclesCompleted.value += 1
        }

        _percentage.value = _drank.value.toFloat() / _goal.toFloat()
    }
}
