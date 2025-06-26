package com.pdmproyecto.mymedicine.data.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pdmproyecto.mymedicine.data.database.entities.PatientEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PatientDao {

    @Query("SELECT * FROM Patient")
    suspend fun getAllPatients(): Flow<List<PatientEntity>>

    @Query("SELECT * FROM Patient WHERE id = :patientId")
    suspend fun getPatientFromId(patientId: Int): PatientEntity?

    @Query("SELECT * FROM Patient WHERE userId = :userId")
    suspend fun getPatientFromUserId(userId: Int): PatientEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPatient(patient: PatientEntity)

    @Query("DELETE FROM Patient WHERE id = :patientId")
    suspend fun removePatientFromId(patientId: Int)

    @Query("DELETE FROM Patient WHERE userId = :userId")
    suspend fun removePatientFromUserId(userId: Int)
}