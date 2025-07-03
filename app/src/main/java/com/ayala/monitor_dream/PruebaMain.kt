package com.ayala.monitor_dream

import android.app.Application
import com.ayala.monitor_dream.data.alarmDataStore.alarmUserDataStore
import com.ayala.monitor_dream.data.alarmrepository.AlarmUserPreferenceRepository


class PruebaMain : Application() {
    lateinit var alarmUserPreferenceRepository: AlarmUserPreferenceRepository
        private set

    override fun onCreate() {
        super.onCreate()
        alarmUserPreferenceRepository = AlarmUserPreferenceRepository(this.alarmUserDataStore)
    }
}
