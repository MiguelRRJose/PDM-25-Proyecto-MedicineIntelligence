package com.pdmproyecto.mymedicine.ui.screens.WaterIntake.navigation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.pdmproyecto.mymedicine.ui.screens.PatientDashboard.components.BottomNavigationBar
import com.pdmproyecto.mymedicine.ui.screens.WaterIntake.WaterIntakeScreen
import com.pdmproyecto.mymedicine.ui.screens.WaterIntake.WaterIntakeViewModel

@Composable
fun WaterIntakeWithBottomBar(navController: NavHostController, username: String) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: ""

    val viewModel: WaterIntakeViewModel = viewModel()

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                username = username,
                currentRoute = currentRoute,
                onItemSelected = { newRoute ->
                    if (newRoute != currentRoute) {
                        navController.navigate(newRoute) {
                            popUpTo("dashboard/$username") { inclusive = false }
                        }
                    }
                },
                onCentralActionClick = { /* decorativo */ }
            )
        }
    ) { padding ->
        WaterIntakeScreen(
            viewModel = viewModel,
            onBackPressed = { navController.popBackStack() }
        )
    }
}
