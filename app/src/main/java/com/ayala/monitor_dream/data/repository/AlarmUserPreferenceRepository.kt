package com.ayala.monitor_dream.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import com.ayala.monitor_dream.navigation.ActualTime
import com.ayala.monitor_dream.navigation.AlarmData
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

    //Funciones "Alarma"

    val alarmUser: Flow<String?> = dataStore.data
        .catch { exception ->
            if (exception is IOException) emit(emptyPreferences())
            else throw exception
        }
        .map { preferences ->
            preferences[ALARM_USER_KEY]
        }

    suspend fun saveAlarmUser(alarmData: AlarmData) {
        val json = Json.encodeToString(alarmData)
        dataStore.edit { preferences ->
            preferences[ALARM_USER_KEY] = json
        }
    }

    //Funciones "Tiempo_Sistema"

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

    suspend fun saveStartTime(actualTime: ActualTime) {
        val json = Json.encodeToString(actualTime)
        dataStore.edit { preferences ->
            preferences[START_TIME_KEY] = json
        }
    }

}
