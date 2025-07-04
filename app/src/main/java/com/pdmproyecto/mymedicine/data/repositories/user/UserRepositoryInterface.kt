package com.pdmproyecto.mymedicine.data.repositories.user

import com.pdmproyecto.mymedicine.data.models.User
import kotlinx.coroutines.flow.Flow

interface UserRepositoryInterface {

    fun getAllUsers(): Flow<List<User>>

    suspend fun getUserFromId(userId: Int): User?

    suspend fun addUser(user: User)

    suspend fun removeUserFromId(userId: Int)

    suspend fun getUserByEmail(email: String): User?

    suspend fun getUserByDui(dui: String): User?

}