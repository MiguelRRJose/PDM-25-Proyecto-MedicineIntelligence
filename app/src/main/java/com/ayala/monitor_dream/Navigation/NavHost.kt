package com.ayala.monitor_dream.Navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import com.ayala.monitor_dream.Screens.SleepAlarmScreen
import com.ayala.monitor_dream.Screens.SleepScreen
import com.ayala.monitor_dream.Screens.SleepTrackingScreen
import com.ayala.monitor_dream.ViewModel.SleepViewModel

@Composable
fun AppNavigator(navController: NavHostController) {
    val sleepViewModel: SleepViewModel = viewModel(factory = SleepViewModel.Factory)

    NavHost(navController = navController, startDestination = Screen.Alarm.route) {

        composable(Screen.Alarm.route) {
            SleepAlarmScreen(
                onSetAlarmClick = { hour, minute, _ ->
                    sleepViewModel.setAlarmTime(hour, minute)
                    navController.navigate(Screen.Sleep.route)
                }
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

        composable(Screen.SleepTracking.route) {
            SleepTrackingScreen(
                navController = navController,
                viewModel = sleepViewModel
            )
        }


    }
}
