package com.pdmproyecto.mymedicine.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.pdmproyecto.mymedicine.ui.screens.Login.LoginPrincipal.LoginScreen
import com.pdmproyecto.mymedicine.ui.screens.Notification.NotificationScreen
import com.pdmproyecto.mymedicine.ui.screens.PatientDashboard.PatientDashboardScreen
import com.pdmproyecto.mymedicine.screens.Settings.SettingsScreen
import com.pdmproyecto.mymedicine.screens.Settings.SettingsViewModel
import com.pdmproyecto.mymedicine.ui.screens.Settings.NotificationSettings.NotificationSettingsScreen
import com.pdmproyecto.mymedicine.ui.screens.Settings.NotificationSettings.NotificationSettingsViewModel

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "login") {

        composable("login") {
            LoginScreen(navController = navController)
        }

        composable("dashboard") {
            PatientDashboardScreen(navController = navController)
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



    }
}
