package com.pdmproyecto.mymedicine.ui.screens.PatientDashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Adjust
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pdmproyecto.mymedicine.R
import com.pdmproyecto.mymedicine.ui.screens.Login.MotivationCard
import com.pdmproyecto.mymedicine.ui.screens.PatientDashboard.components.DashboardHeader
import com.pdmproyecto.mymedicine.ui.screens.PatientDashboard.components.MotivationSection
import com.pdmproyecto.mymedicine.ui.screens.PatientDashboard.components.SummaryCard


@Composable
fun PatientDashboardScreen(viewModel: PatientDashboardViewModel = viewModel()) {
    val username by remember { derivedStateOf { viewModel.username } }

    Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                        .padding(horizontal = 16.dp)
                        .padding(top = WindowInsets.statusBars.asPaddingValues().calculateTopPadding())
                ) {
                    DashboardHeader(username = username)

                    MotivationSection(
                        weatherMessage = viewModel.weatherMessage,
                        motivationalMessage = viewModel.motivationalMessage,
                        onSearchClick = { /* TODO */ }
                    )

                    Spacer(modifier = Modifier.height(24.dp))
                }

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .offset(y = 24.dp)
                        .background(Color(0xFF18515A).copy(alpha = 0.32f))
                        .padding(horizontal = 16.dp, vertical = 95.dp)
                ) {
                    SummaryCard(
                        waterIntake = viewModel.waterIntake,
                        sleepHours = viewModel.sleepHours,
                        steps = viewModel.steps,
                        medicineReminder = viewModel.medicineReminderTime
                    )

                    Spacer(modifier = Modifier.height(72.dp))
                }
            }
        }
    }
}
