package com.pdmproyecto.mymedicine.data.alarmrepository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import com.pdmproyecto.mymedicine.ui.screens.Navigation.ActualTime
import com.pdmproyecto.mymedicine.ui.screens.Navigation.AlarmData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.serialization.json.Json
import java.io.IOException

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
