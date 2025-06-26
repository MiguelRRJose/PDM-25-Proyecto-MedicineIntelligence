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


    NavHost(navController = navController, startDestination = AlarmP) {

        composable<AlarmP> {
            SleepAlarmScreen(
                viewModel = sleepViewModel,
                onSetAlarmClick = { alarmData, reminder ->
                    sleepViewModel.setAlarmTime(alarmData)
                    navController.navigate(SleepY)
                }
            )
        }

        composable<SleepY> {
            SleepScreen(
                viewModel = sleepViewModel,
                navController = navController,
            )
        }

        composable <SleepTrackingOG>
        {
            SleepTrackingScreen(
                navController = navController,
                viewModel = sleepViewModel,
            )
        }

    } }
