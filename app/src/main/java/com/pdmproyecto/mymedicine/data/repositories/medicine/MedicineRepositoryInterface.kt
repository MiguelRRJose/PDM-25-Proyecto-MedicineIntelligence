package com.pdmproyecto.mymedicine.data.repositories.medicine

import com.pdmproyecto.mymedicine.data.models.Medicine
import kotlinx.coroutines.flow.Flow

interface MedicineRepositoryInterface {

    fun getAllMedicines(): Flow<List<Medicine>>

    suspend fun getMedicineFromId(medicineId: Int): Medicine?

    fun getMedicinesFromPatientId(patientId: Int): Flow<List<Medicine>>

    suspend fun addMedicine(medicine: Medicine)

    suspend fun removeMedicine(medicineId: Int)
}