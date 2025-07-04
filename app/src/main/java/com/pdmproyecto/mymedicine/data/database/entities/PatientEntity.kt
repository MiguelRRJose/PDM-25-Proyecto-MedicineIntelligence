package com.pdmproyecto.mymedicine.data.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.pdmproyecto.mymedicine.data.models.Patient
import java.util.Date

@Entity(
    tableName = "Patient",
    foreignKeys = [
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["id"],
            childColumns = ["userId"]
        )
    ]
)
data class PatientEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val userId: Int,
    val water: Int,
    val sleepHours: Float,
    val sleepAlarmHour: Date,
    val weight: Float,
    val steps: Int
)

fun PatientEntity.toDomain(): Patient{
    return Patient(
        id = this.id,
        userId = this.userId,
        water = this.water,
        sleepHours = this.sleepHours,
        sleepAlarmHour = this.sleepAlarmHour,
        weight = this.weight,
        steps = this.steps
    )
}
