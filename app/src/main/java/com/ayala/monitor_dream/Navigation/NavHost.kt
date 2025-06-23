package com.ayala.monitor_dream.Navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ayala.monitor_dream.Screens.SleepAlarmScreen
import com.ayala.monitor_dream.Screens.SleepScreen
import com.ayala.monitor_dream.Screens.SleepTrackingScreen
import com.ayala.monitor_dream.ViewModel.SleepViewModel

@Composable
fun AppNavigator() {
    val navController: NavHostController = rememberNavController()
    val sleepViewModel: SleepViewModel = viewModel()

    NavHost(navController = navController, startDestination = Screen.Alarm.route) {


        composable(Screen.Alarm.route) {
            SleepAlarmScreen(
                onSetAlarmClick = { hour, minute, _ ->
                    navController.navigate(Screen.Sleep.createRoute(hour, minute))
                }
            )
        }

        composable(
            route = Screen.Sleep.route,
            arguments = listOf(
                navArgument("hour") { type = NavType.IntType },
                navArgument("minute") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val hour = backStackEntry.arguments?.getInt("hour") ?: 0
            val minute = backStackEntry.arguments?.getInt("minute") ?: 0

            sleepViewModel.setAlarmTime(hour, minute)

            SleepScreen(
                viewModel = sleepViewModel,
                onStartSleep = {},
                navController = navController,
                onDetailsClick = {} //Debes de ver que agregas aca
            )
        }

        composable(Screen.SleepTracking.route) {
            SleepTrackingScreen(navController = navController)
        }

    }
}
