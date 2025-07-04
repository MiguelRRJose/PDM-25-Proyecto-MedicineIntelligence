package com.pdmproyecto.mymedicine.data.repositories.user


import com.pdmproyecto.mymedicine.data.database.daos.UserDao
import com.pdmproyecto.mymedicine.data.database.entities.toDomain
import com.pdmproyecto.mymedicine.data.models.User
import com.pdmproyecto.mymedicine.data.models.toDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserRepository(private val dao: UserDao): UserRepositoryInterface{
    override fun getAllUsers(): Flow<List<User>> {
        return dao.getAllUsers().map {
                list -> list.map {
                userEntity -> userEntity.toDomain()
        }
        }
    }

    override suspend fun getUserFromId(userId: Int): User? {
        return dao.getUserFromId(userId)?.toDomain()
    }

    override suspend fun addUser(user: User) {
        dao.addUser(user.toDatabase())
    }

    override suspend fun removeUserFromId(userId: Int) {
        dao.removeUserFromId(userId)
    }

    override suspend fun getUserByEmail(email: String): User? {
        return dao.getUserByEmail(email)?.toDomain()
    }

    override suspend fun getUserByDui(dui: String): User? {
        return dao.getUserByDui(dui)?.toDomain()
    }


}