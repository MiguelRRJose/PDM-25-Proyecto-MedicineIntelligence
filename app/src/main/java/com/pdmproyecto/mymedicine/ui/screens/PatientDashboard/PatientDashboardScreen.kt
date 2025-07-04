package com.pdmproyecto.mymedicine.ui.screens.PatientDashboard

import com.pdmproyecto.mymedicine.ui.screens.PatientDashboard.components.cleanRoute
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.pdmproyecto.mymedicine.ui.screens.PatientDashboard.components.BottomNavigationBar
import com.pdmproyecto.mymedicine.ui.screens.PatientDashboard.components.header.DashboardHeader
import com.pdmproyecto.mymedicine.ui.screens.PatientDashboard.components.motivation.MotivationSection
import com.pdmproyecto.mymedicine.ui.screens.PatientDashboard.components.summary.SummaryCard
import com.pdmproyecto.mymedicine.ui.screens.PatientDashboard.components.metrics.PatientMetricsSection
import com.pdmproyecto.mymedicine.ui.screens.PatientDashboard.components.cleanRoute

@Composable
fun PatientDashboardScreen(
    username: String,
    navController: NavHostController,
    viewModel: PatientDashboardViewModel = viewModel()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: "dashboard"
    val currentBaseRoute = cleanRoute(currentRoute)

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomNavigationBar(
                username = username,
                currentRoute = currentBaseRoute,
                onItemSelected = { newRoute ->
                    if (cleanRoute(newRoute) != currentBaseRoute) {
                        navController.navigate(newRoute) {
                            popUpTo("dashboard/$username") { inclusive = false }
                            launchSingleTop = true
                        }
                    }
                },
                onCentralActionClick = {
                    navController.navigate("medicina_chat")
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(horizontal = 16.dp)
                    .padding(top = WindowInsets.statusBars.asPaddingValues().calculateTopPadding())
            ) {
                DashboardHeader(username = username, navController = navController)

                MotivationSection(
                    weatherMessage = viewModel.weatherMessage,
                    motivationalMessage = viewModel.motivationalMessage,
                    onSearchClick = {
                        navController.navigate("add_medicine/1")
                    }
                )

                Spacer(modifier = Modifier.height(10.dp))
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .offset(y = 25.dp)
                    .background(Color(0xFF18515A).copy(alpha = 0.32f))
                    .padding(horizontal = 16.dp, vertical = 45.dp)
            ) {
                SummaryCard(
                    waterIntake = viewModel.waterIntake,
                    sleepHours = viewModel.sleepHours,
                    steps = viewModel.steps,
                    medicineReminder = viewModel.currentMedicineName
                )
                Spacer(modifier = Modifier.height(16.dp))

                PatientMetricsSection(
                    viewModel = viewModel,
                    onStepsClick = {
                        navController.navigate("step_counter")
                    },
                    onWaterClick = {
                        navController.navigate("water_intake/$username")
                    },
                    onSleepClick = {
                        navController.navigate("sleep_monitor")
                    }
                )

                Spacer(modifier = Modifier.height(55.dp))
            }
        }
    }
}
