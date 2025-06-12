package com.pdmproyecto.mymedicine.ui.screens.PatientDashboard.components.bottombar.CurvedBottom

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.unit.*
import androidx.compose.ui.graphics.drawscope.Fill


@Composable
fun CurvedBottomBarBackground(
    color: Color = Color(0xFF165059),
    modifier: Modifier = Modifier
) {
    Canvas(modifier = modifier.fillMaxSize()) {
        val width = size.width
        val height = size.height
        val radius = 40.dp.toPx()
        val centerX = width / 2

        val path = Path().apply {
            moveTo(0f, 0f)
            lineTo(centerX - radius - 30.dp.toPx(), 0f)

            // Curva cóncava central
            cubicTo(
                centerX - radius, 0f,
                centerX - radius, radius,
                centerX, radius
            )
            cubicTo(
                centerX + radius, radius,
                centerX + radius, 0f,
                centerX + radius + 30.dp.toPx(), 0f
            )

            lineTo(width, 0f)
            lineTo(width, height)
            lineTo(0f, height)
            close()
        }

        drawPath(
            path = path,
            color = color,
            style = Fill
        )
    }
}