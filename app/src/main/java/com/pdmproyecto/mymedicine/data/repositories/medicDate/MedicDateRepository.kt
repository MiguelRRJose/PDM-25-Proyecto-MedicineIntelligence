package com.pdmproyecto.mymedicine.data.repositories.medicDate


import com.pdmproyecto.mymedicine.data.database.daos.MedicDateDao
import com.pdmproyecto.mymedicine.data.database.entities.toDomain
import com.pdmproyecto.mymedicine.data.models.MedicDate
import com.pdmproyecto.mymedicine.data.models.toDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MedicDateRepository(private val dao: MedicDateDao): MedicDateRepositoryInterface {
    override suspend fun getMedicDateFromId(medicDateId: Int): MedicDate? {
        return dao.getMedicDatesFromId(medicDateId)?.toDomain()
    }

    override fun getMedicDatesFromDoctorId(doctorId: Int): Flow<List<MedicDate>> {
        return dao.getMedicDatesFromDoctorId(doctorId).map {
                list -> list.map {
                medicDateEntity -> medicDateEntity.toDomain()
        }
        }
    }

    override fun getMedicDatesFromPatientId(patientId: Int): Flow<List<MedicDate>> {
        return dao.getMedicDatesFromPatientId(patientId).map {
                list -> list.map {
                medicDateEntity -> medicDateEntity.toDomain()
        }
        }
    }

    override suspend fun addMedicDate(medicDate: MedicDate) {
        dao.addMedicDate(medicDate.toDatabase())
    }

    override suspend fun removeMedicDateFromId(medicDateId: Int) {
        dao.removeMedicDateFromId(medicDateId)
    }

    override suspend fun removeMedicDateFromDoctorId(doctorId: Int) {
        dao.removeMedicDateFromDoctorId(doctorId)
    }

    override suspend fun removeMedicDateFromPatientId(patientId: Int) {
        dao.removeMedicDateFromPatientId(patientId)
    }
}