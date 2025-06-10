package com.pdmproyecto.mymedicine.ui.screens.Settings.NotificationSettings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.shape.RoundedCornerShape

@Composable
fun NotificationSettingsScreen(
    onBackClick: () -> Unit,
    viewModel: NotificationSettingsViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val generalNotifications by viewModel.generalNotifications.collectAsState()
    val soundNotifications by viewModel.soundNotifications.collectAsState()
    val appointmentReminders by viewModel.appointmentReminders.collectAsState()
    val medicalUpdates by viewModel.medicalUpdates.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
            .background(Color.White)
    ) {
        Spacer(modifier = Modifier.height(70.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Volver",
                    tint = Color(0xFF165059)
                )
            }

            Spacer(modifier = Modifier.width(65.dp))

            Text(
                text = "Ajuste De Notificaciones",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF165059),
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        NotificationSwitch(
            label = "Notificaciones Generales",
            checked = generalNotifications,
            onCheckedChange = viewModel::onGeneralNotificationsChanged
        )

        Spacer(modifier = Modifier.height(12.dp))

        NotificationSwitch(
            label = "Sonido De Notificación",
            checked = soundNotifications,
            onCheckedChange = viewModel::onSoundNotificationsChanged
        )

        Spacer(modifier = Modifier.height(12.dp))

        NotificationSwitch(
            label = "Recordatorio De Citas",
            checked = appointmentReminders,
            onCheckedChange = viewModel::onAppointmentRemindersChanged
        )

        Spacer(modifier = Modifier.height(12.dp))

        NotificationSwitch(
            label = "Actualizaciones Médicas",
            checked = medicalUpdates,
            onCheckedChange = viewModel::onMedicalUpdatesChanged
        )
    }
}

@Composable
fun NotificationSwitch(
    label: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp)),
        shape = RoundedCornerShape(20.dp),
        tonalElevation = 2.dp,
        shadowElevation = 2.dp,
        color = Color.White
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = label,
                fontSize = 16.sp,
                color = Color.Black,
                modifier = Modifier.weight(1f)
            )

            Switch(
                checked = checked,
                onCheckedChange = onCheckedChange,
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.White,
                    checkedTrackColor = Color(0xFF165059),
                    uncheckedThumbColor = Color.White,
                    uncheckedTrackColor = Color.Gray
                )
            )
        }
    }
}
