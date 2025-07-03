package com.carlosrubio_00000822.myapplication.ui.viewmodel

import android.app.Application
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class StepCounterViewModel(app: Application) : AndroidViewModel(app), SensorEventListener {
    private val sensorManager =
        app.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    private val stepSensor: Sensor? =
        sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)

    private var initialStepCount: Float? = null

    private val _steps = MutableStateFlow(0)

    val steps: StateFlow<Int> = _steps

    private val _goalSteps = MutableStateFlow(5000)
    val goalSteps: StateFlow<Int> = _goalSteps

    init {

        stepSensor?.let {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_UI)
        }
    }

    override fun onSensorChanged(event: SensorEvent) {
        if (event.sensor.type == Sensor.TYPE_STEP_COUNTER) {
            val totalSinceReboot = event.values[0]
            if (initialStepCount == null) {
                initialStepCount = totalSinceReboot
            }
            val counted = totalSinceReboot - (initialStepCount ?: totalSinceReboot)

            _steps.value = counted.toInt().coerceAtMost(_goalSteps.value)
        }
    }

    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) = Unit

    override fun onCleared() {
        super.onCleared()
        sensorManager.unregisterListener(this)
    }
}
