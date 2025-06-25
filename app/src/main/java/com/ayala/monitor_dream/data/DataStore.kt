package com.ayala.monitor_dream.data

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore

val Context.alarmUserDataStore by preferencesDataStore(name = "alarm_user")
