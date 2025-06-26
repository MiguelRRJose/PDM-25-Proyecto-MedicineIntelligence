package com.pdmproyecto.mymedicine.data.repositories.medicDate

import com.pdmproyecto.mymedicine.data.models.MedicDate
import kotlinx.coroutines.flow.Flow

interface MedicDateRepositoryInterface {

    suspend fun getMedicDateFromId(medicDateId: Int): MedicDate?

    suspend fun getMedicDatesFromDoctorId(doctorId: Int): Flow<List<MedicDate>>

    suspend fun getMedicDatesFromPatientId(patientId: Int): Flow<List<MedicDate>>

    suspend fun addMedicDate(medicDate: MedicDate)

    suspend fun removeMedicDateFromId(medicDateId: Int)

    suspend fun removeMedicDateFromDoctorId(doctorId: Int)

    suspend fun removeMedicDateFromPatientId(patientId: Int)
}