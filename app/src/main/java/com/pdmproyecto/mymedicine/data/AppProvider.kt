package com.pdmproyecto.mymedicine.data

import android.content.Context
import com.pdmproyecto.mymedicine.data.database.AppDatabase
import com.pdmproyecto.mymedicine.data.repositories.doctor.DoctorRepository
import com.pdmproyecto.mymedicine.data.repositories.doctor.DoctorRepositoryInterface
import com.pdmproyecto.mymedicine.data.repositories.medicDate.MedicDateRepository
import com.pdmproyecto.mymedicine.data.repositories.medicDate.MedicDateRepositoryInterface
import com.pdmproyecto.mymedicine.data.repositories.medicine.MedicineRepository
import com.pdmproyecto.mymedicine.data.repositories.medicine.MedicineRepositoryInterface
import com.pdmproyecto.mymedicine.data.repositories.patient.PatientRepository
import com.pdmproyecto.mymedicine.data.repositories.patient.PatientRepositoryInterface
import com.pdmproyecto.mymedicine.data.repositories.user.UserRepository
import com.pdmproyecto.mymedicine.data.repositories.user.UserRepositoryInterface


class AppProvider(context: Context) {
    private val appDatabase = AppDatabase.getDatabase(context)

    private val userDao = appDatabase.UserDao()
    private val patientDao = appDatabase.PatientDao()
    private val doctorDao = appDatabase.DoctorDao()
    private val medicDateDao = appDatabase.MedicDateDao()
    private val medicineDao = appDatabase.MedicineDao()

    private val userRepository = UserRepository(userDao)
    private val patientRepository = PatientRepository(patientDao)
    private val doctorRepository = DoctorRepository(doctorDao)
    private val medicDateRepository = MedicDateRepository(medicDateDao)
    private val medicineRepository = MedicineRepository(medicineDao)

    fun provideUserRepository(): UserRepositoryInterface{
        return userRepository
    }

    fun providePatientRepository(): PatientRepositoryInterface{
        return patientRepository
    }

    fun provideDoctorRepository(): DoctorRepositoryInterface{
        return doctorRepository
    }

    fun provideMedicDateRepository(): MedicDateRepositoryInterface{
        return medicDateRepository
    }

    fun provideMedicineRepository(): MedicineRepositoryInterface{
        return medicineRepository
    }
}