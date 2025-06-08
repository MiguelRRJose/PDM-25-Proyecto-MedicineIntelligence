package com.pdmproyecto.mymedicine.ui.screens.PatientDashboard.components.header

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pdmproyecto.mymedicine.R

@Composable
fun DashboardHeader(username: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = R.drawable.cuenta),
                contentDescription = "Usuario",
                modifier = Modifier.size(32.dp),
                tint = Color(0xFF18515A)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(text = "Hola, $username.", fontSize = 16.sp, color = Color.Black)
                Text(text = "Hoy estás en buena forma.", fontSize = 14.sp, color = Color.Gray)
            }
        }

        Row {
            IconButton(onClick = { /* TODO */ }) {
                Icon(Icons.Default.Notifications, "Notificaciones", tint = Color(0xFF18515A))
            }
            IconButton(onClick = { /* TODO */ }) {
                Icon(Icons.Default.Settings, "Configuración", tint = Color(0xFF18515A))
            }
        }
    }
}
