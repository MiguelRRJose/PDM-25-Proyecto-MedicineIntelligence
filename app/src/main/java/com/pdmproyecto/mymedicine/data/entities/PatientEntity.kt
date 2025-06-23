package com.pdmproyecto.mymedicine.data.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.Date

@Entity(
    tableName = "Patient",
    foreignKeys = [
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["id"],
            childColumns = ["userId"]
        )
    ])
data class PatientEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val userId: Int,
    val water: Int,
    val sleepHours: Float,
    val sleepAlarmHour: Date,
    val weight: Float,
    val steps: Int
)
