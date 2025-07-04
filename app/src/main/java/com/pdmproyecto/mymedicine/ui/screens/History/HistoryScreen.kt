package com.pdmproyecto.mymedicine.ui.screens.History

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.pdmproyecto.mymedicine.R
import com.pdmproyecto.mymedicine.ui.screens.PatientDashboard.components.BottomNavigationBar
import com.pdmproyecto.mymedicine.ui.screens.PatientDashboard.components.cleanRoute

@Composable
fun HistoryScreen(
    items: List<MedicineHistory>,
    onDelete: (MedicineHistory) -> Unit,
    username: String,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = cleanRoute(navBackStackEntry?.destination?.route)

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                username = username,
                currentRoute = currentRoute,
                onItemSelected = { newRoute ->
                    if (cleanRoute(newRoute) != currentRoute) {
                        navController.navigate(newRoute) {
                            popUpTo("dashboard/$username") { inclusive = false }
                        }
                    }
                },
                onCentralActionClick = { /* IA o acción decorativa */ }
            )
        }
    ) { padding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(top = 32.dp)
                .padding(padding)
        ) {
            // Logo centrado
            Image(
                painter = painterResource(R.drawable.ic_history),
                contentDescription = null,
                modifier = Modifier
                    .size(64.dp)
                    .align(Alignment.CenterHorizontally)
            )

            Spacer(Modifier.height(8.dp))

            // Título centrado, negrita e itálica
            Text(
                text = "Historial de medicina",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic,
                color = Color.Black,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(Modifier.height(16.dp))

            LazyColumn(
                contentPadding = PaddingValues(vertical = 8.dp, horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(items) { item ->
                    HistoryItemCard(item, onDelete)
                }
            }
        }
    }
}

@Composable
private fun HistoryItemCard(
    item: MedicineHistory,
    onDelete: (MedicineHistory) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = Color(0xFFCCCCCC),
                shape = RoundedCornerShape(16.dp)
            ),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Icono de pastilla
            Image(
                painter = painterResource(R.drawable.ic_pill),
                contentDescription = null,
                modifier = Modifier.size(28.dp)
            )

            Spacer(Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "${item.name} ${item.dosage}",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    text = "Tomado: ${item.dateTime}",
                    fontSize = 14.sp,
                    color = Color(0xFF115C5C)
                )
                Spacer(Modifier.height(2.dp))
                Text(
                    text = "Frecuencia: ${item.frequency}",
                    fontSize = 14.sp,
                    color = Color(0xFF115C5C)
                )
            }

            IconButton(
                onClick = { onDelete(item) },
                modifier = Modifier.size(32.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Delete,
                    contentDescription = "Eliminar",
                    tint = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}
