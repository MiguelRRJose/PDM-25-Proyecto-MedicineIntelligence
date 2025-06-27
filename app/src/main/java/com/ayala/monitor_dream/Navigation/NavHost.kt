package com.ayala.monitor_dream.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import com.ayala.monitor_dream.screens.SleepAlarmScreen
import com.ayala.monitor_dream.screens.SleepScreen
import com.ayala.monitor_dream.screens.SleepTrackingScreen
import com.ayala.monitor_dream.viewModel.SleepViewModel

@Composable
fun AppNavigator(navController: NavHostController) {
    val sleepViewModel: SleepViewModel = viewModel(factory = SleepViewModel.Factory)

    NavHost(navController = navController, startDestination = AlarmP) {

        composable<AlarmP> {
            SleepAlarmScreen(
                viewModel = sleepViewModel,
                onSetAlarmClick = { alarmData, actualTime,sleepDuration ->

                    sleepViewModel.setAlarmTime(alarmData)
                    sleepViewModel.setStartTime(actualTime)
                    sleepViewModel.setDuration(sleepDuration)

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
