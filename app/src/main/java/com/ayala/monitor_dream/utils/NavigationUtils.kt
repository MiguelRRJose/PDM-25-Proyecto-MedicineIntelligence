package com.ayala.monitor_dream.utils

import androidx.navigation.NavController
import com.ayala.monitor_dream.navigation.AlarmData
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

fun navigateToSleepTracking(navController: NavController, alarmData: AlarmData) {

    val json = Json.encodeToString(alarmData)

    val encoded = URLEncoder.encode(json, StandardCharsets.UTF_8.toString())

}
