package com.pdmproyecto.mymedicine.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.pdmproyecto.mymedicine.ViewModel.SleepViewModel
import com.pdmproyecto.mymedicine.data.repositories.medicine.MedicineRepository
import com.pdmproyecto.mymedicine.screens.Settings.ChangePassword.ChangePasswordScreen
import com.pdmproyecto.mymedicine.screens.Settings.ChangePassword.ChangePasswordViewModel
import com.pdmproyecto.mymedicine.ui.screens.Login.LoginPrincipal.LoginScreen
import com.pdmproyecto.mymedicine.ui.screens.Notification.NotificationScreen
import com.pdmproyecto.mymedicine.ui.screens.PatientDashboard.PatientDashboardScreen
import com.pdmproyecto.mymedicine.screens.Settings.SettingsScreen
import com.pdmproyecto.mymedicine.screens.Settings.SettingsViewModel
import com.pdmproyecto.mymedicine.ui.screens.History.HistoryScreen
import com.pdmproyecto.mymedicine.ui.screens.History.HistoryViewModel
import com.pdmproyecto.mymedicine.ui.screens.MedicineAlarms.MedicineAlarmsScreen
import com.pdmproyecto.mymedicine.ui.screens.Settings.NotificationSettings.NotificationSettingsScreen
import com.pdmproyecto.mymedicine.ui.screens.Settings.NotificationSettings.NotificationSettingsViewModel
import com.pdmproyecto.mymedicine.ui.screens.StepCounter.StepCounterScreen
import com.pdmproyecto.mymedicine.ui.screens.WaterIntake.navigation.WaterIntakeWithBottomBar
import com.pdmproyecto.mymedicine.ui.screens.MyIA.ChatWithAiScreen
import com.pdmproyecto.mymedicine.ui.screens.Sleep.SleepAlarmScreen
import com.pdmproyecto.mymedicine.ui.screens.Sleep.SleepDetail
import com.pdmproyecto.mymedicine.ui.screens.Sleep.SleepScreen
import com.pdmproyecto.mymedicine.ui.screens.Sleep.SleepTrackingScreen
import com.pdmproyecto.mymedicine.ui.screens.StepCounter.StepCounterViewModel

@Composable
fun AppNavigation(navController: NavHostController) {

    NavHost(navController = navController, startDestination = "login") {

        composable("login") {
            LoginScreen(navController = navController)
        }

        composable("dashboard/{username}") { backStackEntry ->
            val username = backStackEntry.arguments?.getString("username") ?: ""
            PatientDashboardScreen(username = username, navController = navController)
        }

        composable("notifications") {
            NotificationScreen(navController = navController)
        }

        composable("settings") {
            val settingsViewModel: SettingsViewModel = viewModel()
            SettingsScreen(
                viewModel = settingsViewModel,
                onBackClick = { navController.popBackStack() },
                onNotificationClick = { navController.navigate("notification_settings") },
                onChangePasswordClick = {
                    // Navegación a cambiar contraseña (puedes crear una pantalla luego)
                    navController.navigate("change_password")
                },
                onLogoutClick = {
                    navController.navigate("login") {
                        popUpTo("dashboard") { inclusive = true }
                    }
                }
            )
        }

        composable("notification_settings") {
            val notificationSettingsViewModel: NotificationSettingsViewModel = viewModel()
            NotificationSettingsScreen(
                onBackClick = { navController.popBackStack() },
                viewModel = notificationSettingsViewModel
            )
        }

        composable("change_password") {
            val changePasswordViewModel: ChangePasswordViewModel = viewModel()
            ChangePasswordScreen(
                onBackClick = { navController.popBackStack() },
                viewModel = changePasswordViewModel
            )
        }

        composable("step_counter") {
            val stepViewModel: StepCounterViewModel = viewModel()

            StepCounterScreen(
                steps = stepViewModel.steps.collectAsState().value,
                goalSteps = stepViewModel.goalSteps.collectAsState().value,
                onBackPressed = { navController.popBackStack() }
            )
        }


        composable("water_intake/{username}") { backStackEntry ->
            val username = backStackEntry.arguments?.getString("username") ?: ""
            WaterIntakeWithBottomBar(navController, username)
        }


        composable("medicina/{username}") { backStackEntry ->
            val username = backStackEntry.arguments?.getString("username") ?: ""
            MedicineAlarmsScreen(navController = navController, username = username)
        }


        composable("medicina_chat") {
            ChatWithAiScreen(navController = navController)
        }

        composable("historial/{username}") { backStackEntry ->
            val username = backStackEntry.arguments?.getString("username") ?: ""
            val historyViewModel: HistoryViewModel = viewModel()

            HistoryScreen(
                items = historyViewModel.historyList.collectAsState().value,
                onDelete = { historyViewModel.deleteItem(it) },
                username = username,
                navController = navController
            )
        }


        composable("sleep_monitor") {
            val sleepViewModel: SleepViewModel = viewModel(factory = SleepViewModel.Factory)
            SleepAlarmScreen(
                viewModel = sleepViewModel,
                navController = navController
            )
        }

        composable("sleep_screen") {
            val sleepViewModel: SleepViewModel = viewModel(factory = SleepViewModel.Factory)
            SleepScreen(
                viewModel = sleepViewModel,
                navController = navController
            )
        }

        composable("sleep_summary") {
            val sleepViewModel: SleepViewModel = viewModel(factory = SleepViewModel.Factory)
            SleepTrackingScreen(
                viewModel = sleepViewModel,
                navController = navController
            )
        }

        composable("sleep_detail") {
            val sleepViewModel: SleepViewModel = viewModel(factory = SleepViewModel.Factory)
            SleepDetail(
                navController = navController,
                viewModel = sleepViewModel
            )
        }





















    }
}
