package com.pdmproyecto.mymedicine.ui.screens.PatientDashboard.components.motivation

import androidx.compose.runtime.Composable
import com.pdmproyecto.mymedicine.ui.screens.Login.components.MotivationCard

@Composable
fun MotivationSection(
    weatherMessage: String,
    motivationalMessage: String,
    onSearchClick: () -> Unit
) {
    MotivationCard(
        messageTop = weatherMessage,
        messageBottom = motivationalMessage,
        onSearchClick = onSearchClick
    )
}
