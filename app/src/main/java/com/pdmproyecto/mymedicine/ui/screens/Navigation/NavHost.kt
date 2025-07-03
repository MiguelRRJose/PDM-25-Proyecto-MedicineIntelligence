package com.pdmproyecto.mymedicine.ui.screens.Navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.pdmproyecto.mymedicine.ViewModel.SleepViewModel
import com.pdmproyecto.mymedicine.ui.screens.Sleep.SleepAlarmScreen
import com.pdmproyecto.mymedicine.ui.screens.Sleep.SleepDetail
import com.pdmproyecto.mymedicine.ui.screens.Sleep.SleepScreen
import com.pdmproyecto.mymedicine.ui.screens.Sleep.SleepTrackingScreen

@Composable
fun AppNavigator(navController: NavHostController) {
    val sleepViewModel: SleepViewModel = viewModel(factory = SleepViewModel.Factory)
    NavHost(navController = navController, startDestination = AlarmP) {

        composable<AlarmP> {
            SleepAlarmScreen(
                viewModel = sleepViewModel,
                navController = navController,
            )
        }

        composable<SleepY> {
            SleepScreen(
                viewModel = sleepViewModel,
                navController = navController,
            )
        }

        composable <SleepSummary>
        {
            SleepTrackingScreen(
                navController = navController,
                viewModel = sleepViewModel,
            )
        }
        composable<SleepDetail>
        {
            SleepDetail(
                navController = navController,
                viewModel = sleepViewModel,
            )
        }
    } }
