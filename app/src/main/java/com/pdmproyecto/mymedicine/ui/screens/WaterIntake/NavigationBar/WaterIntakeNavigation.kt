package com.pdmproyecto.mymedicine.ui.screens.WaterIntake.navigation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.pdmproyecto.mymedicine.ui.screens.PatientDashboard.components.BottomNavigationBar
import com.pdmproyecto.mymedicine.ui.screens.WaterIntake.WaterIntakeScreen

@Composable
fun WaterIntakeWithBottomBar(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: ""

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                currentRoute = currentRoute,
                onItemSelected = { newRoute ->
                    if (newRoute != currentRoute) {
                        navController.navigate(newRoute) {
                            popUpTo("water_intake") { inclusive = false }
                            launchSingleTop = true
                        }
                    }
                },
                onCentralActionClick = { /* decorativo */ }
            )
        }
    ) { padding ->
        WaterIntakeScreen(
            drank = 4,
            goal = 8,
            percentage = 0.5f,
            onDrinkClick = { /* sumar agua */ },
            onBackPressed = { navController.popBackStack() },
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        )
    }
}
