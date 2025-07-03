package com.pdmproyecto.mymedicine.ui.screens.PatientDashboard.components.metrics

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.pdmproyecto.mymedicine.R
import com.pdmproyecto.mymedicine.ui.screens.PatientDashboard.PatientDashboardViewModel

@Composable
fun PatientMetricsSection(
    viewModel: PatientDashboardViewModel,
    onStepsClick: () -> Unit,
    onWaterClick: () -> Unit,
    onSleepClick: () -> Unit){


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
                modifier = Modifier
                    .weight(1f)
                    .clickable { onWaterClick() }
            )
            MetricCard(
                title = "Sueño",
                mainText = "${viewModel.sleepHours} H",
                subText = "Calidad: ${viewModel.sleepQuality}",
                modifier = Modifier
                    .weight(1f)
                    .clickable { onSleepClick() } // 👈 navegación al tocar la tarjeta
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
                modifier = Modifier
                    .weight(1f)
                    .clickable { onStepsClick() }
            )
            WeightCard(
                weight = viewModel.weight,
                goalPercent = viewModel.weightGoalPercent,
                modifier = Modifier.weight(1f)
            )
        }
    }
}
