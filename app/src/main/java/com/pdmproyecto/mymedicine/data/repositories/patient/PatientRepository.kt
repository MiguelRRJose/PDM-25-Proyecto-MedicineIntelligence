package com.pdmproyecto.mymedicine.data.repositories.patient

import com.pdmproyecto.mymedicine.data.database.daos.PatientDao
import com.pdmproyecto.mymedicine.data.database.entities.toDomain
import com.pdmproyecto.mymedicine.data.models.Patient
import com.pdmproyecto.mymedicine.data.models.toDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PatientRepository(private val dao: PatientDao): PatientRepositoryInterface {
    override fun getAllPatients(): Flow<List<Patient>> {
        return dao.getAllPatients().map {
            list -> list.map {
                patientEntity -> patientEntity.toDomain()
        }
        }
    }

    override suspend fun getPatientFromId(patientId: Int): Patient? {
        return dao.getPatientFromId(patientId)?.toDomain()
    }

    override suspend fun getPatientFromUserId(userId: Int): Patient? {
        return dao.getPatientFromUserId(userId)?.toDomain()
    }

    override suspend fun addPatient(patient: Patient) {
        dao.addPatient(patient.toDatabase())
    }

    override suspend fun removePatientFromId(patientId: Int) {
        dao.removePatientFromId(patientId)
    }

    override suspend fun removePatientFromUserId(userId: Int) {
        dao.removePatientFromUserId(userId)
    }
}