package com.pdmproyecto.mymedicine.data.models

import com.pdmproyecto.mymedicine.data.database.entities.MedicineEntity
import java.io.Serializable

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
): Serializable

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

fun Medicine.getIntervalMillis():Long {
    var millis: Long = 0L
    if (timeLapUnit.equals("minutos")) millis = timeLap * 60000L
    if (timeLapUnit.equals("horas")) millis = timeLap * 60 * 60000L
    if (timeLapUnit.equals("dias"))  millis = timeLap * 24 * 60 * 60000L
    if (timeLapUnit.equals("semanas")) millis = timeLap * 7 * 24 * 60 * 60000L

    return millis
}