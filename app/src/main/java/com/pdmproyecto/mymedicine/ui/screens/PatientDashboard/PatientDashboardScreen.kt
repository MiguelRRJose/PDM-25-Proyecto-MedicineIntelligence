package com.pdmproyecto.mymedicine.ui.screens.PatientDashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.pdmproyecto.mymedicine.ui.screens.PatientDashboard.components.BottomNavigationBar
import com.pdmproyecto.mymedicine.ui.screens.PatientDashboard.components.header.DashboardHeader
import com.pdmproyecto.mymedicine.ui.screens.PatientDashboard.components.motivation.MotivationSection
import com.pdmproyecto.mymedicine.ui.screens.PatientDashboard.components.summary.SummaryCard
import com.pdmproyecto.mymedicine.ui.screens.PatientDashboard.components.metrics.PatientMetricsSection
import androidx.navigation.compose.currentBackStackEntryAsState



@Composable
fun PatientDashboardScreen(
    navController: NavHostController,
    viewModel: PatientDashboardViewModel = viewModel()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    var currentRoute by rememberSaveable { mutableStateOf("dashboard") } // o la que quieras como seleccionada por defecto
    val username by remember { derivedStateOf { viewModel.username } }
    var selectedItem by remember { mutableStateOf("inicio") }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomNavigationBar(
                currentRoute = currentRoute,
                onItemSelected = { newRoute -> currentRoute = newRoute },
                onCentralActionClick = { /* decorativo */ }
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
                    onSearchClick = { /* TODO */ }
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
                    medicineReminder = viewModel.medicineReminderTime
                )
                Spacer(modifier = Modifier.height(16.dp))
                PatientMetricsSection(viewModel = viewModel)
                Spacer(modifier = Modifier.height(55.dp))
            }


        }

    }
}

