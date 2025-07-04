package com.pdmproyecto.mymedicine


import android.app.Application
import android.util.Log
import android.util.Log.println
import com.pdmproyecto.mymedicine.data.AppProvider
import com.pdmproyecto.mymedicine.data.alarmDataStore.alarmUserDataStore
import com.pdmproyecto.mymedicine.data.alarmrepository.AlarmUserPreferenceRepository


class MyMedicineApplication : Application() {

    val appProvider by lazy {
        AppProvider(this)
    }

    // Lo que traía PruebaMain
    lateinit var alarmUserPreferenceRepository: AlarmUserPreferenceRepository
        private set

    override fun onCreate() {
        super.onCreate()

        // Parte original tuya
        try {
            val userRepository = appProvider.provideUserRepository()
            val patientRepository = appProvider.providePatientRepository()
            val doctorRepository = appProvider.provideDoctorRepository()
            val medicineRepository = appProvider.provideMedicineRepository()
            val medicDateRepository = appProvider.provideMedicDateRepository()
            println(Log.ASSERT, "application", "Repositorios inicializados correctamente")
        } catch (e: Exception) {
            println(Log.ASSERT, "application","Error al inicializar AppProvider: ${e.message}")
            e.printStackTrace()
        }

        // Parte que traía PruebaMain
        alarmUserPreferenceRepository = AlarmUserPreferenceRepository(this.alarmUserDataStore)
    }
}
