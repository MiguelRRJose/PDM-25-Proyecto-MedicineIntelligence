package com.pdmproyecto.mymedicine.ui.screens.PatientDashboard.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.*
import com.pdmproyecto.mymedicine.R

@Composable
fun BottomNavigationBar(
    currentRoute: String,
    onItemSelected: (String) -> Unit
) {
    val items = listOf(
        BottomNavItem("inicio", R.drawable.homebar, "Inicio"),
        BottomNavItem("agua", R.drawable.aguabar, "Agua"),
        BottomNavItem("medicina", R.drawable.meds, "MyMedicine"),
        BottomNavItem("historial", R.drawable.historybar, "Historial")
    )

    Box {


        // Fondo de la BottomBar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(72.dp)
                .background(Color(0xFF18515A)),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            items.take(2).forEach { item ->
                BottomNavItemView(item, currentRoute, onItemSelected)
            }

            Spacer(modifier = Modifier.width(64.dp))

            items.drop(2).forEach { item ->
                BottomNavItemView(item, currentRoute, onItemSelected)
            }
        }

    }
}

@Composable
fun BottomNavItemView(
    item: BottomNavItem,
    currentRoute: String,
    onItemSelected: (String) -> Unit
) {

    val isSelected = currentRoute == item.route
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable { onItemSelected(item.route) }
            .padding(top = 4.dp)
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
