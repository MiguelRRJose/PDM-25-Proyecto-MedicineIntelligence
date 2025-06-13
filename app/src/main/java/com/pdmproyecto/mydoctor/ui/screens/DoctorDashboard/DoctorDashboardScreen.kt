package com.pdmproyecto.mydoctor.ui.screens.DoctorDashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.background
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.pdmproyecto.mydoctor.R

@Composable
fun DoctorDashboardScreen(viewModel: DoctorDashboardViewModel = viewModel(), navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Encabezado
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text("Bienvenido,", fontSize = 18.sp)
                Text(
                    text = viewModel.doctorName,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Image(
                painter = painterResource(id = R.drawable.logo1),
                contentDescription = "Logo",
                modifier = Modifier.size(48.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
        Divider()

        Spacer(modifier = Modifier.height(16.dp))
        Text("Pacientes del día", fontSize = 16.sp, fontWeight = FontWeight.Medium)

        Spacer(modifier = Modifier.height(12.dp))

        LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            items(viewModel.pacientesDelDia) { paciente ->
                Card(
                    modifier = Modifier
                        .width(180.dp),
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(12.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(id = paciente.imagenId),
                            contentDescription = paciente.nombre,
                            modifier = Modifier
                                .size(60.dp)
                                .clip(CircleShape)
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(paciente.nombre, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                        Text(paciente.problema, color = MaterialTheme.colorScheme.primary, fontSize = 12.sp)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(paciente.hora, fontSize = 12.sp)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Métricas
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                MetricCard("Total de pacientes", viewModel.totalPacientes.toString())
                MetricCard("En tratamiento", viewModel.enTratamiento.toString())
            }
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                MetricCard("Lo más recetado", viewModel.medicamentoFrecuente)
                MetricCard("Promedio de sueño", "${viewModel.promedioSueno}\n${viewModel.calidadSueno}%")
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        // Botón +
        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            FloatingActionButton(
                onClick = { /* acción futura */ },
                containerColor = Color(0xFF165059)
            ) {
                Text("+", color = Color.White, fontSize = 28.sp)
            }
        }
    }
}

@Composable
fun MetricCard(title: String, value: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(85.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(title, fontSize = 12.sp)
            Spacer(modifier = Modifier.height(6.dp))
            Text(value, fontWeight = FontWeight.Bold, fontSize = 16.sp)
        }
    }
}

