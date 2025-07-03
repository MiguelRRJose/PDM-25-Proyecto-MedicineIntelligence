package com.pdmproyecto.mymedicine.data.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.pdmproyecto.mymedicine.data.models.Doctor

@Entity(
    tableName = "Doctor",
    foreignKeys = [
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["id"],
            childColumns = ["userId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class DoctorEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val userId: Int
)

fun DoctorEntity.toDomain(): Doctor{
    return Doctor(
        id = this.id,
        userId = this.userId
    )
}