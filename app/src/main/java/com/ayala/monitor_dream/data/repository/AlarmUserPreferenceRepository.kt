package com.ayala.monitor_dream.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import com.ayala.monitor_dream.model.ActualTime
import kotlinx.coroutines.flow.*
import kotlinx.serialization.json.Json
import java.io.IOException
import kotlinx.serialization.encodeToString


class AlarmUserPreferenceRepository(
    private val dataStore: DataStore<Preferences>)
{
    companion object {
        private val ALARM_USER_KEY = stringPreferencesKey("alarm_user")
        private val START_TIME_KEY = stringPreferencesKey("actual_time")
    }


    suspend fun saveStartTime(actualTime: ActualTime) {
        val json = Json.encodeToString(actualTime)
        dataStore.edit { preferences ->
            preferences[START_TIME_KEY] = json
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

    suspend fun saveAlarmUser(value: String) {
        dataStore.edit { preferences ->
            preferences[ALARM_USER_KEY] = value
        }
    }

    val startTime: Flow<ActualTime?> = dataStore.data
        .catch { exception ->
            if (exception is IOException) emit(emptyPreferences())
            else throw exception
        }
        .map { preferences ->
            preferences[START_TIME_KEY]?.let { json ->
                runCatching {
                    Json.decodeFromString<ActualTime>(json)
                }.getOrNull()
            }
        }



}
