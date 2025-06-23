package com.ayala.monitor_dream.Navigation

import android.R.attr.defaultValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.ayala.monitor_dream.Screens.SleepAlarmScreen
import com.ayala.monitor_dream.Screens.SleepScreen
import com.ayala.monitor_dream.Screens.SleepTrackingScreen
import com.ayala.monitor_dream.ViewModel.SleepViewModel

@Composable
fun AppNavigator(navController: NavHostController) {
    val sleepViewModel: SleepViewModel = viewModel()

    NavHost(navController = navController, startDestination = Screen.Alarm.route) {

        // Pantalla de configurar alarma
        composable(Screen.Alarm.route) {
            SleepAlarmScreen(
                onSetAlarmClick = { hour, minute, _ ->
                    sleepViewModel.setAlarmTime(hour, minute)
                    navController.navigate(Screen.Sleep.route)
                }
            )
        }

        // Pantalla principal de sueño (previo al seguimiento)
        composable(Screen.Sleep.route) {
            SleepScreen(
                viewModel = sleepViewModel,
                navController = navController,
                onDetailsClick = {
                    // Puedes agregar otra pantalla luego si lo deseas
                }
            )
        }

        // Pantalla de seguimiento de sueño (donde aparece “despertar”)
        composable(Screen.SleepTracking.route) {
            SleepTrackingScreen(
                navController = navController,
                viewModel = sleepViewModel
            )
        }


    }
}
