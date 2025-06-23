package com.pdmproyecto.mymedicine.data.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "Doctor",
    foreignKeys = [
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["id"],
            childColumns = ["userId"],
            onDelete = ForeignKey.CASCADE
        )
    ])
data class DoctorEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val userId: Int
)
