package com.pdmproyecto.mymedicine.data.repositories.doctor

import com.pdmproyecto.mymedicine.data.database.daos.DoctorDao
import com.pdmproyecto.mymedicine.data.database.entities.toDomain
import com.pdmproyecto.mymedicine.data.models.Doctor
import com.pdmproyecto.mymedicine.data.models.toDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DoctorRepository(private val dao: DoctorDao): DoctorRepositoryInterface{
    override suspend fun getAllDoctors(): Flow<List<Doctor>> {
        return dao.getAllDoctors().map {
                list -> list.map {
                doctorEntity -> doctorEntity.toDomain()
        }
        }
    }

    override suspend fun getDoctorFromId(doctorId: Int): Doctor? {
        return dao.getDoctorFromId(doctorId)?.toDomain()
    }

    override suspend fun getDoctorFromUserId(userId: Int): Doctor? {
        return dao.getDoctorFromUserId(userId)?.toDomain()
    }

    override suspend fun addDoctor(doctor: Doctor) {
        dao.addDoctor(doctor.toDatabase())
    }

    override suspend fun removeDoctorFromId(doctorId: Int) {
        dao.removeDoctorFromId(doctorId)
    }

    override suspend fun removeDoctorFromUserId(userId: Int) {
        dao.removeDoctorFromUserId(userId)
    }
}