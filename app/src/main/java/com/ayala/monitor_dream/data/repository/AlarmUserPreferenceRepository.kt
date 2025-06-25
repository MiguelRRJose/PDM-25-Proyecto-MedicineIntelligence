package com.ayala.monitor_dream.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import kotlinx.coroutines.flow.*
import java.io.IOException

class AlarmUserPreferenceRepository(
    private val dataStore: DataStore<Preferences>)
{
    companion object {
        private val ALARM_USER_KEY = stringPreferencesKey("alarm_user")
    }

    suspend fun saveAlarmUser(value: String) {
        dataStore.edit { preferences ->
            preferences[ALARM_USER_KEY] = value
        }
    }

    val alarmUser: Flow<String> = dataStore.data
        .catch { exception ->
            if (exception is IOException) emit(emptyPreferences())
            else throw exception
        }
        .map { preferences ->
            preferences[ALARM_USER_KEY] ?: "06:00 AM"
        }


}
