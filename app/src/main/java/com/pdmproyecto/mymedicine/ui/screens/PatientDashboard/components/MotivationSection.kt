package com.pdmproyecto.mymedicine.ui.screens.PatientDashboard.components

import androidx.compose.runtime.Composable
import com.pdmproyecto.mymedicine.ui.screens.Login.MotivationCard

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
