package com.pdmproyecto.mymedicine.ui.screens.PatientDashboard.components.summary

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pdmproyecto.mymedicine.R

@Composable
fun SummaryCard(
    waterIntake: Int,
    sleepHours: Float,
    steps: Int,
    medicineReminder: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 50.dp, vertical = 8.dp),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                text = "Resumen del día",
                style = MaterialTheme.typography.titleSmall.copy(
                    color = Color(0xFF18515A),
                    fontSize = 16.sp
                )
            )

            Spacer(modifier = Modifier.height(12.dp))

            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    SummaryItem(R.drawable.agua, "$waterIntake/8")
                    SummaryItem(R.drawable.lunas, "${sleepHours} H")
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    SummaryItem(R.drawable.huellas, "$steps")
                    SummaryItem(R.drawable.medicina, medicineReminder)
                }
            }
        }
    }
}

@Composable
fun SummaryItem(iconRes: Int, text: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = null,
            tint = Color(0xFF18515A),
            modifier = Modifier.size(28.dp)
        )
        Text(text = text, fontSize = 14.sp)
    }
}
