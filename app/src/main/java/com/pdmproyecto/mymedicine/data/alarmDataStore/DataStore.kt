package com.pdmproyecto.mymedicine.data.alarmDataStore

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore

val Context.alarmUserDataStore by preferencesDataStore(name = "alarm_user")
