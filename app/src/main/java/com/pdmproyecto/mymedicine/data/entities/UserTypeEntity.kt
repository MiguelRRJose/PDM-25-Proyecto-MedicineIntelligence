package com.pdmproyecto.mymedicine.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "UserType")
data class UserTypeEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String
)
