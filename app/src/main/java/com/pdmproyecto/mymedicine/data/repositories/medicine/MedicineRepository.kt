package com.pdmproyecto.mymedicine.data.repositories.medicine

import com.pdmproyecto.mymedicine.data.database.daos.MedicineDao
import com.pdmproyecto.mymedicine.data.database.entities.toDomain
import com.pdmproyecto.mymedicine.data.models.Medicine
import com.pdmproyecto.mymedicine.data.models.toDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MedicineRepository(private val dao: MedicineDao): MedicineRepositoryInterface {
    override fun getAllMedicines(): Flow<List<Medicine>> {
        return dao.getAllMedicines().map {
            list -> list.map {
                medicineEntity -> medicineEntity.toDomain()
        }
        }
    }

    override suspend fun getMedicineFromId(medicineId: Int): Medicine? {
        return dao.getMedicineFromId(medicineId)?.toDomain()
    }

    override fun getMedicinesFromPatientId(patientId: Int): Flow<List<Medicine>> {
        return dao.getMedicinesFromPatientId(patientId).map {
            list -> list.map {
                medicineEntity -> medicineEntity.toDomain()
        }
        }
    }

    override suspend fun addMedicine(medicine: Medicine) {
        dao.addMedicine(medicine.toDatabase())
    }

    override suspend fun removeMedicine(medicineId: Int) {
        dao.removeMedicine(medicineId)
    }
}