package com.pdmproyecto.mymedicine.data.repositories.patient

import com.pdmproyecto.mymedicine.data.models.Patient
import kotlinx.coroutines.flow.Flow

interface PatientRepositoryInterface {

    fun getAllPatients(): Flow<List<Patient>>

    suspend fun getPatientFromId(patientId: Int): Patient?

    suspend fun getPatientFromUserId(userId: Int): Patient?

    suspend fun addPatient(patient: Patient)

    suspend fun removePatientFromId(patientId: Int)

    suspend fun removePatientFromUserId(userId: Int)
}