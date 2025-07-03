package com.ayala.monitor_dream.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity (tableName = "data_graph")
data class UserSleepData (
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var label: String,
    var value: Double
)
