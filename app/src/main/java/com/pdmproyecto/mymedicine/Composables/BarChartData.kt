package com.pdmproyecto.mymedicine.Composables

import androidx.compose.ui.graphics.Color

data class BarChartData(
    val bars: List<Bar>
) {
    data class Bar(
        val label: String,
        val value: Float,
        val color: Color
    )
}