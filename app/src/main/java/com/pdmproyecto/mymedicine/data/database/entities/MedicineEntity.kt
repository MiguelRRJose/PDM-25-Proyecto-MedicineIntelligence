package com.pdmproyecto.mymedicine.data.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.pdmproyecto.mymedicine.data.models.Medicine
import java.util.Date


@Entity(
    tableName = "Medicine",
    foreignKeys = [
        ForeignKey(
            entity = PatientEntity::class,
            parentColumns = ["id"],
            childColumns = ["patientId"]
        )
    ]
)
data class MedicineEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val patientId: Int,
    val name: String,
    val startDate: Date,
    val finishDate: Date,
    val timeLap: Int,
    val timeLapUnit: String,
    val amount: Float,
    val unit: String
)

fun MedicineEntity.toDomain(): Medicine{
    return Medicine(
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