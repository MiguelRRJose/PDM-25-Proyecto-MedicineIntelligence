package com.pdmproyecto.mymedicine.data.models

import com.pdmproyecto.mymedicine.data.database.entities.MedicDateEntity
import java.util.Date

data class MedicDate(
    val id: Int,
    val doctorId: Int,
    val patientId: Int,
    val dateTime: Date
)

fun MedicDate.toDatabase(): MedicDateEntity{
    return MedicDateEntity(
        id = this.id,
        doctorId = this.doctorId,
        patientId = this.patientId,
        dateTime = this.dateTime
    )
}