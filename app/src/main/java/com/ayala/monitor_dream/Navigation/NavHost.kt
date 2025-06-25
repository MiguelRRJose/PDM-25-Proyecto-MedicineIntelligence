package com.ayala.monitor_dream.Navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

import com.ayala.monitor_dream.Screens.SleepAlarmScreen
import com.ayala.monitor_dream.Screens.SleepScreen
import com.ayala.monitor_dream.Screens.SleepTrackingScreen
import com.ayala.monitor_dream.ViewModel.SleepViewModel
import com.ayala.monitor_dream.model.AlarmData
import com.ayala.monitor_dream.utils.navigateToSleepTracking
import kotlinx.serialization.json.Json

@Composable
fun AppNavigator(navController: NavHostController) {
    val sleepViewModel: SleepViewModel = viewModel(factory = SleepViewModel.Factory)


    NavHost(navController = navController, startDestination = Screen.Alarm.route) {

        composable(
            route = "sleep_tracking/{alarmDataJson}",
            arguments = listOf(navArgument("alarmDataJson") { type = NavType.StringType })
        ) { backStackEntry ->

            val json = backStackEntry.arguments?.getString("alarmDataJson") ?: ""

            val alarmData = try {
                Json.decodeFromString<AlarmData>(java.net.URLDecoder.decode(json, "UTF-8"))
            } catch (e: Exception) {
                e.printStackTrace()
                AlarmData(6, 0)
            }

            SleepTrackingScreen(
                navController = navController,
                alarmData = alarmData,
                viewModel = sleepViewModel
            )
        }

        composable(Screen.Sleep.route) {
            SleepScreen(
                viewModel = sleepViewModel,
                navController = navController,
                onDetailsClick = {
                }
            )
        }

        composable(Screen.Alarm.route) {
            SleepAlarmScreen(
                viewModel = sleepViewModel,
                onSetAlarmClick = { alarmData, reminder ->
                    sleepViewModel.setAlarmTime(alarmData)
                    navController.navigate(Screen.Sleep.route)
                }
            )
        }

    } }
