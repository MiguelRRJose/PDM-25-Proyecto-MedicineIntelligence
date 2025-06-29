package com.pdmproyecto.mymedicine

import android.app.Application
import com.pdmproyecto.mymedicine.data.AppProvider

class MyMedicineApplication: Application() {

    val appProvider by lazy {
        AppProvider(this)
    }

    override fun onCreate() {
        super.onCreate()

        val userRepository = appProvider.provideUserRepository()
        val patientRepository = appProvider.providePatientRepository()
        val doctorRepository = appProvider.provideDoctorRepository()
        val medicineRepository = appProvider.provideMedicineRepository()
        val medicDateRepository = appProvider.provideMedicDateRepository()
    }
}