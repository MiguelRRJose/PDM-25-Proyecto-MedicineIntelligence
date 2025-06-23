package com.ayala.monitor_dream.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "alarm_prefs")

class AlarmPreferences(private val context: Context) {

    companion object {
        val ALARM_TIME_KEY = stringPreferencesKey("alarm_time")
    }

    val alarmTimeFlow: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[ALARM_TIME_KEY] ?: "06:00 AM"
        }

    suspend fun saveAlarmTime(value: String) {
        context.dataStore.edit { preferences ->
            preferences[ALARM_TIME_KEY] = value
        }
    }
}
