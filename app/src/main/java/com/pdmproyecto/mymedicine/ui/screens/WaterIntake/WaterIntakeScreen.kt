package com.pdmproyecto.mymedicine.ui.screens.WaterIntake

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pdmproyecto.mymedicine.R

@Composable
fun WaterIntakeScreen(
    viewModel: WaterIntakeViewModel = viewModel(),
    onBackPressed: () -> Unit
) {
    val drank = viewModel.drank.collectAsState().value
    val percentage = viewModel.percentage.collectAsState().value
    val cycles = viewModel.cyclesCompleted.collectAsState().value

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        content = { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color(0xFFE6ECEC))
                    .padding(innerPadding)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    // ← Flechita volver
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(onClick = onBackPressed) {
                            Icon(
                                Icons.Filled.ArrowBack,
                                contentDescription = "Volver",
                                tint = Color(0xFF115C5C)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    Text(
                        text = "Es hora de beber agua",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFF115C5C)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    SemiCircleProgress(
                        drank = drank,
                        goal = 8,
                        size = 200.dp,
                        strokeWidth = 20.dp,
                        circleColor = Color(0xFFBEBEBE),
                        progressColor = Color(0xFF03BFC0),
                        dropDrawableRes = R.drawable.ic_drop
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Button(
                        onClick = { viewModel.onDrink() },
                        shape = CircleShape,
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF03BFC0)),
                        modifier = Modifier
                            .width(180.dp)
                            .height(50.dp)
                    ) {
                        Text(
                            text = "Bebiendo",
                            fontSize = 16.sp,
                            color = Color.White,
                            fontWeight = FontWeight.SemiBold
                        )
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    FullCircleProgress(
                        percentage = percentage,
                        size = 150.dp,
                        strokeWidth = 18.dp,
                        circleColor = Color(0xFFEEEEEE),
                        progressColor = Color(0xFF03BFC0)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Ciclos completados: $cycles",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF115C5C)
                    )

                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    )
}


@Composable
fun SemiCircleProgress(
    drank: Int,
    goal: Int,
    size: Dp,
    strokeWidth: Dp,
    circleColor: Color,
    progressColor: Color,
    dropDrawableRes: Int
) {
    val sweepAngle = if (goal > 0) {
        (drank.toFloat() / goal.toFloat()).coerceIn(0f, 1f) * 180f
    } else 0f

    Box(
        modifier = Modifier.size(size),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val diameter = size.toPx()
            val topLeft = Offset(0f, 0f)

            // Semicírculo de fondo
            drawArc(
                color = circleColor,
                startAngle = 180f,
                sweepAngle = 180f,
                useCenter = false,
                topLeft = topLeft,
                size = androidx.compose.ui.geometry.Size(diameter, diameter),
                style = Stroke(width = strokeWidth.toPx(), cap = StrokeCap.Round)
            )

            // Semicírculo de progreso
            drawArc(
                color = progressColor,
                startAngle = 180f,
                sweepAngle = sweepAngle,
                useCenter = false,
                topLeft = topLeft,
                size = androidx.compose.ui.geometry.Size(diameter, diameter),
                style = Stroke(width = strokeWidth.toPx(), cap = StrokeCap.Round)
            )
        }

        // Texto central con vasos
        Text(
            text = "$drank/$goal",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF115C5C)
        )

        // Icono gota debajo
        Image(
            painter = painterResource(id = dropDrawableRes),
            contentDescription = "Gota de agua",
            modifier = Modifier
                .size(36.dp)
                .align(Alignment.BottomCenter)
                .offset(y = (strokeWidth / 2) * 1.1f)
        )
    }
}

@Composable
fun FullCircleProgress(
    percentage: Float,
    size: Dp,
    strokeWidth: Dp,
    circleColor: Color,
    progressColor: Color
) {
    val sweepAngle = (percentage.coerceIn(0f, 1f)) * 360f

    Box(
        modifier = Modifier.size(size),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val diameter = size.toPx()
            val topLeft = Offset(0f, 0f)

            // Círculo fondo completo
            drawArc(
                color = circleColor,
                startAngle = 270f,
                sweepAngle = 360f,
                useCenter = false,
                topLeft = topLeft,
                size = androidx.compose.ui.geometry.Size(diameter, diameter),
                style = Stroke(width = strokeWidth.toPx(), cap = StrokeCap.Round)
            )

            // Círculo de progreso
            drawArc(
                color = progressColor,
                startAngle = 270f,
                sweepAngle = sweepAngle,
                useCenter = false,
                topLeft = topLeft,
                size = androidx.compose.ui.geometry.Size(diameter, diameter),
                style = Stroke(width = strokeWidth.toPx(), cap = StrokeCap.Round)
            )
        }

        // Texto % en el centro
        val percentText = "${(percentage * 100).toInt()}%"
        Text(
            text = percentText,
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF115C5C)
        )
    }
}

