package com.ayala.monitor_dream

import android.app.Application
import com.ayala.monitor_dream.data.alarmUserDataStore
import com.ayala.monitor_dream.data.repository.AlarmUserPreferenceRepository


class PruebaMain : Application() {
    lateinit var alarmUserPreferenceRepository: AlarmUserPreferenceRepository
        private set

    override fun onCreate() {
        super.onCreate()
        alarmUserPreferenceRepository = AlarmUserPreferenceRepository(this.alarmUserDataStore)
    }
}
