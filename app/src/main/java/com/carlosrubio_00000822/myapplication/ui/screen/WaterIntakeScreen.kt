// WaterIntakeScreen.kt
package com.carlosrubio_00000822.myapplication.ui.screen

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
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
import com.carlosrubio_00000822.myapplication.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WaterIntakeScreen(
    drank: Int = 4,
    goal: Int = 8,
    percentage: Float = 0.77f, // 0.77 = 77%
    onDrinkClick: () -> Unit = {},
    onBackPressed: () -> Unit = {},
    modifier: Modifier = Modifier            // <-- Agregamos el parámetro modifier
) {
    Scaffold(
        modifier = modifier       // <-- Aplicamos el modifier al Scaffold
            .fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { /* vacío, solo ícono de volver */ },
                navigationIcon = {
                    IconButton(onClick = { onBackPressed() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_home),
                            contentDescription = "Volver",
                            tint = Color(0xFF115C5C),
                            modifier = Modifier.size(24.dp)
                        )
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color.Transparent,
                    navigationIconContentColor = Color(0xFF115C5C)
                ),
                modifier = Modifier
                    .height(56.dp)
            )
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color(0xFFE6ECEC))
                    .padding(innerPadding),    // <-- padding para que no tape la TopAppBar
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(24.dp, alignment = Alignment.Top)
            ) {
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Es hora de beber agua",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF115C5C)
                )

                SemiCircleProgress(
                    drank = drank,
                    goal = goal,
                    size = 200.dp,
                    strokeWidth = 20.dp,
                    circleColor = Color(0xFFBEBEBE),
                    progressColor = Color(0xFF03BFC0),
                    dropDrawableRes = R.drawable.ic_drop
                )

                Button(
                    onClick = onDrinkClick,
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

                Spacer(modifier = Modifier.height(16.dp))

                FullCircleProgress(
                    percentage = percentage,
                    size = 150.dp,
                    strokeWidth = 18.dp,
                    circleColor = Color(0xFFEEEEEE),
                    progressColor = Color(0xFF03BFC0)
                )
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
        modifier = Modifier
            .size(size),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val diameter = size.toPx()
            val topLeft = Offset(0f, 0f)

            drawArc(
                color = circleColor,
                startAngle = 180f,
                sweepAngle = 180f,
                useCenter = false,
                topLeft = topLeft,
                size = androidx.compose.ui.geometry.Size(diameter, diameter),
                style = Stroke(width = strokeWidth.toPx(), cap = StrokeCap.Round)
            )

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

        Text(
            text = "$drank/$goal",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF115C5C)
        )

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
        modifier = Modifier
            .size(size),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val diameter = size.toPx()
            val topLeft = Offset(0f, 0f)

            drawArc(
                color = circleColor,
                startAngle = 270f,
                sweepAngle = 360f,
                useCenter = false,
                topLeft = topLeft,
                size = androidx.compose.ui.geometry.Size(diameter, diameter),
                style = Stroke(width = strokeWidth.toPx(), cap = StrokeCap.Round)
            )

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

        val percentText = "${(percentage * 100).toInt()}%"
        Text(
            text = percentText,
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF115C5C)
        )
    }
}
