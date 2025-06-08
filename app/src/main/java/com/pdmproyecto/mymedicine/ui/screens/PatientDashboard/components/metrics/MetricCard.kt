package com.pdmproyecto.mymedicine.ui.screens.PatientDashboard.components.metrics

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MetricCard(
    title: String,
    icon: Painter? = null,
    mainText: String,
    subText: String? = null,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .height(110.dp),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = title, fontSize = 14.sp, color = Color.Black)

            icon?.let {
                Image(
                    painter = it,
                    contentDescription = null,
                    modifier = Modifier.size(28.dp)
                )
            }

            Text(
                text = mainText,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF18515A)
            )

            subText?.let {
                Text(
                    text = it,
                    fontSize = 13.sp,
                    color = Color(0xFF1D8D8D),
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}
