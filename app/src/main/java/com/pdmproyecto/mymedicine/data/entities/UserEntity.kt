package com.pdmproyecto.mymedicine.data.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "User",
    foreignKeys = [
        ForeignKey(
            entity = UserTypeEntity::class,
            parentColumns = ["id"],
            childColumns = ["typeId"],
            onDelete = ForeignKey.CASCADE
        )
    ])
data class UserEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val typeId: Int,
    val email: String,
    val phoneNumber: String,
    val DUI: String,
    val age: Int
)
