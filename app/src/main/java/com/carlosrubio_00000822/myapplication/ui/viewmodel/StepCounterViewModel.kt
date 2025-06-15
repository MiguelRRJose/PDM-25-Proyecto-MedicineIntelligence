package com.carlosrubio_00000822.myapplication.ui.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class StepCounterViewModel : ViewModel() {
    private val _steps     = MutableStateFlow(0)
    private val _goalSteps = MutableStateFlow(5000)

    val steps: Int     get() = _steps.value
    val goalSteps: Int get() = _goalSteps.value

    fun addStep(count: Int = 1) {
        _steps.value = (_steps.value + count).coerceAtMost(_goalSteps.value)
    }
}

