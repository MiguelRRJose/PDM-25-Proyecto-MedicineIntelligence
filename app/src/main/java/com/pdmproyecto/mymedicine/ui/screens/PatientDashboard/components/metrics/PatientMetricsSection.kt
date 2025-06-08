package com.pdmproyecto.mymedicine.ui.screens.PatientDashboard.components.metrics

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.pdmproyecto.mymedicine.R
import com.pdmproyecto.mymedicine.ui.screens.PatientDashboard.PatientDashboardViewModel

@Composable
fun PatientMetricsSection(viewModel: PatientDashboardViewModel) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            MetricCard(
                title = "Agua",
                icon = painterResource(id = R.drawable.agua),
                mainText = "Bebé mucha agua",
                modifier = Modifier.weight(1f)
            )
            MetricCard(
                title = "Sueño",
                mainText = "${viewModel.sleepHours} H",
                subText = "Calidad: ${viewModel.sleepQuality}",
                modifier = Modifier.weight(1f)
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            MetricCard(
                title = "Actividad",
                mainText = "${viewModel.steps} pasos",
                subText = "38 min activo",
                modifier = Modifier.weight(1f)
            )
            WeightCard(
                weight = viewModel.weight,
                goalPercent = viewModel.weightGoalPercent,
                modifier = Modifier.weight(1f)
            )
        }
    }
}
