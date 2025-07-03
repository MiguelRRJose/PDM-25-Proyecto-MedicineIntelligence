package com.pdmproyecto.mymedicine.ui.screens.WaterIntake

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class WaterIntakeViewModel : ViewModel() {
    private val _drank      = MutableStateFlow(4)
    private val _goal       = MutableStateFlow(8)
    private val _percentage = MutableStateFlow(_drank.value.toFloat() / _goal.value)

    val drank: Int       get() = _drank.value
    val goal: Int        get() = _goal.value
    val percentage: Float get() = _percentage.value

    fun onDrink() {
        _drank.value = (_drank.value + 1).coerceAtMost(_goal.value)
        _percentage.value = _drank.value.toFloat() / _goal.value
    }
}
