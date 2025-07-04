package com.pdmproyecto.mymedicine.data.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pdmproyecto.mymedicine.data.database.entities.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Query("SELECT * FROM User")
    fun getAllUsers(): Flow<List<UserEntity>>

    @Query("SELECT * FROM User WHERE id = :userId")
    suspend fun getUserFromId(userId: Int): UserEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUser(user: UserEntity)

    @Query("DELETE FROM User WHERE id = :userId")
    suspend fun removeUserFromId(userId: Int)

    @Query("SELECT * FROM User WHERE email = :email LIMIT 1")
    suspend fun getUserByEmail(email: String): UserEntity?

    @Query("SELECT * FROM User WHERE dui = :dui LIMIT 1")
    suspend fun getUserByDui(dui: String): UserEntity?

}