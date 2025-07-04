package com.pdmproyecto.mymedicine.ui.screens.PatientDashboard.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class BottomNavItem(
    val route: String,
    @DrawableRes val icon: Int,
    val label: String
)
