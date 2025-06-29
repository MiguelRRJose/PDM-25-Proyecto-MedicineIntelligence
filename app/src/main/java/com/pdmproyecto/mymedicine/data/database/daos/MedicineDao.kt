package com.pdmproyecto.mymedicine.data.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pdmproyecto.mymedicine.data.database.entities.MedicineEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MedicineDao {

    @Query("SELECT * FROM Medicine")
    fun getAllMedicines(): Flow<List<MedicineEntity>>

    @Query("SELECT * FROM Medicine WHERE id = :medicineId")
    suspend fun getMedicineFromId(medicineId: Int): MedicineEntity?

    @Query("SELECT * FROM Medicine WHERE patientId = :patientId")
    fun getMedicinesFromPatientId(patientId: Int): Flow<List<MedicineEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMedicine(medicine: MedicineEntity)

    @Query("DELETE FROM Medicine WHERE id = :medicineId")
    suspend fun removeMedicine(medicineId: Int)
}