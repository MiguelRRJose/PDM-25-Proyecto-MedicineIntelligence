package com.pdmproyecto.mymedicine.ui.screens.PatientDashboard.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.*
import com.pdmproyecto.mymedicine.R
import com.pdmproyecto.mymedicine.ui.screens.PatientDashboard.components.bottombar.CurvedBottom.CurvedBottomBarBackground

// 🔧 Función para limpiar ruta base (sin argumentos)
fun cleanRoute(route: String?): String {
    return route?.substringBefore("/") ?: ""
}

@Composable
fun BottomNavigationBar(
    username: String,
    currentRoute: String,
    onItemSelected: (String) -> Unit,
    onCentralActionClick: () -> Unit
) {
    // 🔁 Ítems con rutas dinámicas usando el username
    val items = listOf(
        BottomNavItem("dashboard/$username", R.drawable.homebar, "Inicio"),
        BottomNavItem("water_intake/$username", R.drawable.aguabar, "Agua"),
        BottomNavItem("medicina/$username", R.drawable.meds, "MyMedicine"),
        BottomNavItem("historial/$username", R.drawable.historybar, "Historial")
    )

    // Limpieza de la ruta actual para comparaciones
    val currentBaseRoute = cleanRoute(currentRoute)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)
    ) {
        CurvedBottomBarBackground(
            modifier = Modifier
                .fillMaxWidth()
                .height(72.dp)
                .align(Alignment.BottomCenter)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(72.dp)
                .align(Alignment.BottomCenter),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            items.take(2).forEach { item ->
                BottomNavItemView(item, currentBaseRoute, onItemSelected)
            }

            Spacer(modifier = Modifier.width(64.dp))

            items.drop(2).forEach { item ->
                BottomNavItemView(item, currentBaseRoute, onItemSelected)
            }
        }

        FloatingActionButton(
            onClick = { onCentralActionClick() },
            shape = CircleShape,
            containerColor = Color(0xFF165059),
            modifier = Modifier
                .size(64.dp)
                .align(Alignment.TopCenter)
                .offset(y = (-32).dp)
        ) {
            Text(
                text = "AI",
                color = Color.White,
                fontSize = 18.sp
            )
        }
    }
}

@Composable
fun BottomNavItemView(
    item: BottomNavItem,
    currentBaseRoute: String,
    onItemSelected: (String) -> Unit
) {
    val isSelected = currentBaseRoute == cleanRoute(item.route)

    val itemModifier = if (isSelected) {
        Modifier
            .background(
                color = Color(0xFFB0BEC5).copy(alpha = 0.4f),
                shape = RoundedCornerShape(12.dp)
            )
            .padding(horizontal = 12.dp, vertical = 6.dp)
    } else {
        Modifier
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable { onItemSelected(item.route) }
            .padding(top = 4.dp)
            .then(itemModifier)
    ) {
        Image(
            painter = painterResource(id = item.icon),
            contentDescription = item.label,
            modifier = Modifier.size(24.dp)
        )
        Text(
            text = item.label,
            fontSize = 11.sp,
            color = if (isSelected) Color.White else Color(0xFFB0BEC5)
        )
    }
}
