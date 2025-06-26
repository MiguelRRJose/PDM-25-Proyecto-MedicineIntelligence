package com.pdmproyecto.mymedicine.data.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.pdmproyecto.mymedicine.data.models.User

@Entity(
    tableName = "User"
)
data class UserEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val email: String,
    val phoneNumber: String,
    val DUI: String,
    val age: Int,
    val password: String
)

fun UserEntity.toDomain(): User{
    return User(
        id = this.id,
        name = this.name,
        email = this.email,
        phoneNumber = this.phoneNumber,
        DUI = this.DUI,
        age = this.age,
        password = this.password
    )
}