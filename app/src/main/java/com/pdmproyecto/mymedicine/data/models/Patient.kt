package com.pdmproyecto.mymedicine.data.models

import com.pdmproyecto.mymedicine.data.database.entities.PatientEntity
import java.util.Date

data class Patient(
    val id: Int,
    val userId: Int,
    val water: Int,
    val sleepHours: Float,
    val sleepAlarmHour: Date,
    val weight: Float,
    val steps: Int
)

fun Patient.toDatabase(): PatientEntity{
    return PatientEntity(
        id = this.id,
        userId = this.userId,
        water = this.water,
        sleepHours = this.sleepHours,
        sleepAlarmHour = this.sleepAlarmHour,
        weight = this.weight,
        steps = this.steps
    )
}
