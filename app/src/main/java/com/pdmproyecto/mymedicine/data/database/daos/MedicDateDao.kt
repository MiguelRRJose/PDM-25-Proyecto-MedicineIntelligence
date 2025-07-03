package com.pdmproyecto.mymedicine.data.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pdmproyecto.mymedicine.data.database.entities.MedicDateEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MedicDateDao {

    @Query("SELECT * FROM MedicDate WHERE id = :medicDateId")
    suspend fun getMedicDatesFromId(medicDateId: Int): MedicDateEntity?

    @Query("SELECT * FROM MedicDate WHERE doctorId = :doctorId")
    fun getMedicDatesFromDoctorId(doctorId: Int): Flow<List<MedicDateEntity>>

    @Query("SELECT * FROM MedicDate WHERE patientId = :patientId")
    fun getMedicDatesFromPatientId(patientId: Int): Flow<List<MedicDateEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMedicDate(medicDate: MedicDateEntity)

    @Query("DELETE FROM MedicDate WHERE id = :medicDateId")
    suspend fun removeMedicDateFromId(medicDateId: Int)

    @Query("DELETE FROM MedicDate WHERE doctorId = :doctorId")
    suspend fun removeMedicDateFromDoctorId(doctorId: Int)

    @Query("DELETE FROM MedicDate WHERE patientId = :patientId")
    suspend fun removeMedicDateFromPatientId(patientId: Int)
}