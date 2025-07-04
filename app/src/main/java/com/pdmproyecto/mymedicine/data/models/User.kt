package com.pdmproyecto.mymedicine.data.models

import com.pdmproyecto.mymedicine.data.database.entities.UserEntity

data class User(
    val id: Int,
    val name: String,
    val email: String,
    val phoneNumber: String,
    val dui: String,
    val age: Int,
    val password: String
)

fun User.toDatabase(): UserEntity{
    return UserEntity(
        id = this.id,
        name = this.name,
        email = this.email,
        phoneNumber = this.phoneNumber,
        dui = this.dui,
        age = this.age,
        password = this.password
    )
}