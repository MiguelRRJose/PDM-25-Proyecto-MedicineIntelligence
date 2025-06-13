package com.pdmproyecto.mydoctor.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.pdmproyecto.mydoctor.ui.screens.DoctorDashboard.DoctorDashboardScreen
import com.pdmproyecto.mydoctor.ui.screens.Login.LoginPrincipal.DoctorLoginScreen

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "login") {

        composable("login") {
            DoctorLoginScreen(navController = navController)
        }

        composable("doctor_dashboard") {
            DoctorDashboardScreen(navController = navController)
        }


    }
}
