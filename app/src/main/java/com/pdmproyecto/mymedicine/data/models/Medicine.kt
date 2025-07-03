package com.pdmproyecto.mymedicine.data.models

import com.pdmproyecto.mymedicine.data.database.entities.MedicineEntity

data class Medicine(
    val id: Int,
    val patientId: Int,
    val name: String,
    val unit: String,
    val amount: Float,
    val startDate: java.util.Date,
    val finishDate: java.util.Date?,
    val timeLap: Int,
    val timeLapUnit: String
)

fun Medicine.toDatabase(): MedicineEntity{
    return MedicineEntity(
        id = this.id,
        patientId = this.patientId,
        name = this.name,
        unit = this.unit,
        amount = this.amount,
        startDate = this.startDate,
        finishDate = this.finishDate,
        timeLap = this.timeLap,
        timeLapUnit = this.timeLapUnit
    )
}