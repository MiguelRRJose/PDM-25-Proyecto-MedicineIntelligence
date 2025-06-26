package com.pdmproyecto.mymedicine.data.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pdmproyecto.mymedicine.data.database.entities.DoctorEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DoctorDao {

    @Query("SELECT * FROM Doctor")
    suspend fun getAllDoctors(): Flow<List<DoctorEntity>>

    @Query("SELECT * FROM Doctor WHERE id = :doctorId")
    suspend fun getDoctorFromId(doctorId: Int): DoctorEntity?

    @Query("SELECT * FROM Doctor WHERE userId = :userId")
    suspend fun getDoctorFromUserId(userId: Int): DoctorEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addDoctor(doctor: DoctorEntity)

    @Query("DELETE FROM Doctor WHERE id = :doctorId")
    suspend fun removeDoctorFromId(doctorId: Int)

    @Query("DELETE FROM Doctor WHERE userId = :userId")
    suspend fun removeDoctorFromUserId(userId: Int)
}