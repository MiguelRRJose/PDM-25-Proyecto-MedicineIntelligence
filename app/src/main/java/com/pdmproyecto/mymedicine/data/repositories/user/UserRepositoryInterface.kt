package com.pdmproyecto.mymedicine.data.repositories.user

import com.pdmproyecto.mymedicine.data.models.User
import kotlinx.coroutines.flow.Flow

interface UserRepositoryInterface {

    suspend fun getAllUsers(): Flow<List<User>>

    suspend fun getUserFromId(userId: Int): User?

    suspend fun addUser(user: User)

    suspend fun removeUserFromId(userId: Int)
}