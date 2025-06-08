package com.pdmproyecto.mymedicine.ui.screens.PatientDashboard.components.metrics

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun WeightCard(weight: Float, goalPercent: Int, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .height(110.dp),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 12.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(text = "Peso", fontSize = 14.sp, color = Color.Black)
                Text(
                    text = "$weight kg",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color.Black
                )
            }

            Box(contentAlignment = Alignment.Center) {
                CircularProgressIndicator(
                    progress = goalPercent / 100f,
                    strokeWidth = 4.dp,
                    color = Color(0xFF18515A),
                    modifier = Modifier.size(40.dp)
                )
                Text(
                    text = "$goalPercent%",
                    fontSize = 12.sp,
                    color = Color.DarkGray
                )
            }
        }
    }
}

