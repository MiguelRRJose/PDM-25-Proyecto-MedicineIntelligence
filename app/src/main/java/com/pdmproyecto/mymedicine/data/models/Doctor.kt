package com.pdmproyecto.mymedicine.data.models

import com.pdmproyecto.mymedicine.data.database.entities.DoctorEntity

data class Doctor(
    val id: Int,
    val userId: Int
)

fun Doctor.toDatabase(): DoctorEntity{
    return DoctorEntity(
        id = this.id,
        userId = this.userId
    )
}