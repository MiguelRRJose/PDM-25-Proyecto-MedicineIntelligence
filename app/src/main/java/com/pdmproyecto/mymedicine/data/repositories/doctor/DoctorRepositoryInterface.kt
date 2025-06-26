package com.pdmproyecto.mymedicine.data.repositories.doctor

import com.pdmproyecto.mymedicine.data.models.Doctor
import kotlinx.coroutines.flow.Flow

interface DoctorRepositoryInterface {

    suspend fun getAllDoctors(): Flow<List<Doctor>>

    suspend fun getDoctorFromId(doctorId: Int): Doctor?

    suspend fun getDoctorFromUserId(userId: Int): Doctor?

    suspend fun addDoctor(doctor: Doctor)

    suspend fun removeDoctorFromId(doctorId: Int)

    suspend fun removeDoctorFromUserId(userId: Int)
}