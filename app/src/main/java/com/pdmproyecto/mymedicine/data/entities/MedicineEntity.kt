package com.pdmproyecto.mymedicine.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "Medicine")
data class MedicineEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val startDate: Date,
    val finishDate: Date,
    val amount: Float,
    val unit: String
)
