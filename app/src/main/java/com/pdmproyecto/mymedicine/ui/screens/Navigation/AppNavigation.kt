package com.pdmproyecto.mymedicine.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.pdmproyecto.mymedicine.ui.screens.Login.LoginPrincipal.LoginScreen
import com.pdmproyecto.mymedicine.ui.screens.Notification.NotificationScreen
import com.pdmproyecto.mymedicine.ui.screens.PatientDashboard.PatientDashboardScreen

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "login") {
        composable("login") { LoginScreen(navController) }
        composable("dashboard") { PatientDashboardScreen(navController = navController) }
        composable("notifications") { NotificationScreen(navController = navController) }

    }
}
