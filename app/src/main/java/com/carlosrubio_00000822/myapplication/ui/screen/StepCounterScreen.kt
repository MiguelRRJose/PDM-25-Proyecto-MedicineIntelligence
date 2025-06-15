package com.carlosrubio_00000822.myapplication.ui.screen

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DirectionsWalk
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Whatshot
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight

@Composable
fun StepCounterScreen(
    steps: Int,
    goalSteps: Int,
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(modifier = modifier.fillMaxSize()) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF9F9F9))
                .padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(16.dp))

            // Flecha de volver manual
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

            Spacer(Modifier.height(8.dp))

            // Círculo punteado + progreso
            Box(contentAlignment = Alignment.Center) {
                val size = 260.dp
                Canvas(modifier = Modifier.size(size)) {
                    val dashStroke = Stroke(
                        width = 12f,
                        cap = StrokeCap.Round,
                        pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 8f), 0f)
                    )
                    // Fondo punteado
                    drawArc(
                        color = Color(0xFFCCCCCC),
                        startAngle = -90f,
                        sweepAngle = 360f,
                        useCenter = false,
                        style = dashStroke
                    )
                    // Progreso sólido
                    val sweep = (steps.toFloat() / goalSteps).coerceIn(0f,1f) * 360f
                    drawArc(
                        color = Color(0xFF03BFC0),
                        startAngle = -90f,
                        sweepAngle = sweep,
                        useCenter = false,
                        style = Stroke(width = 12f, cap = StrokeCap.Round)
                    )
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        Icons.Filled.DirectionsWalk,
                        contentDescription = null,
                        tint = Color(0xFF115C5C),
                        modifier = Modifier.size(32.dp)
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(
                        text = "%,d".format(steps),
                        fontSize = 40.sp,
                        color = Color(0xFF115C5C),
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Pasos diarios",
                        fontSize = 14.sp,
                        color = Color(0xFF667070)
                    )
                }
            }

            Spacer(Modifier.height(16.dp))

            // Métricas secundarias
            Row(
                horizontalArrangement = Arrangement.spacedBy(24.dp),
                modifier = Modifier.padding(horizontal = 32.dp)
            ) {
                MetricItem(Icons.Filled.Whatshot, "239 kcal")
                MetricItem(Icons.Filled.AccessTime, "30 min")
                MetricItem(Icons.Filled.Place, "3 km")
            }

            Spacer(Modifier.height(16.dp))

            // Tarjeta de mensaje
            Card(
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .height(100.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Vier, 19 abril", color = Color.White)
                        Text("90%", color = Color(0xFF03BFC0))
                    }
                    Text("Gran trabajo!", color = Color.White)
                    LinearProgressIndicator(
                        progress = 0.9f,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(6.dp),
                        trackColor = Color(0xFFEEEEEE),
                        color = Color(0xFF03BFC0)
                    )
                }
            }

            Spacer(Modifier.weight(1f))

            // Botón de acción
            Button(
                onClick = { /* He terminado */ },
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .height(50.dp),
                shape = RoundedCornerShape(25.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF115C5C))
            ) {
                Text("He terminado", color = Color.White)
            }

            Spacer(Modifier.height(24.dp))
        }
    }
}

@Composable
private fun MetricItem(icon: ImageVector, label: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(icon, contentDescription = null, tint = Color(0xFF03BFC0), modifier = Modifier.size(20.dp))
        Spacer(Modifier.width(4.dp))
        Text(label, fontSize = 12.sp, color = Color(0xFF667070))
    }
}

