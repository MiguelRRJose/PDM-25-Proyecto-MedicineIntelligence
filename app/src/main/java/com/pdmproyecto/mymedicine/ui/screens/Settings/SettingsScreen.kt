package com.pdmproyecto.mymedicine.screens.Settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.VpnKey
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel,
    onBackClick: () -> Unit,
    onNotificationClick: () -> Unit,
    onChangePasswordClick: () -> Unit,
    onLogoutClick: () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()

    // Recoger eventos del ViewModel
    LaunchedEffect(Unit) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is SettingsViewModel.SettingsUiEvent.GoToNotificationSettings -> onNotificationClick()
                is SettingsViewModel.SettingsUiEvent.GoToChangePassword -> onChangePasswordClick()
                is SettingsViewModel.SettingsUiEvent.Logout -> onLogoutClick()
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(55.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBackClick) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Volver", tint = Color(0xFF165059))
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Ajustes",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF165059),
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        SettingsOption(
            icon = Icons.Default.Lightbulb,
            label = "Ajuste De Notificaciones",
            onClick = {
                coroutineScope.launch {
                    viewModel.onNotificationSettingsClicked()
                }
            }
        )


        SettingsOption(
            icon = Icons.Default.VpnKey,
            label = "Cambiar Contraseña",
            onClick = {
                coroutineScope.launch {
                    viewModel.onChangePasswordClicked()
                }
            }
        )

        SettingsOption(
            icon = Icons.Default.Person,
            label = "Cerrar Sesión",
            onClick = {
                coroutineScope.launch {
                    viewModel.onLogoutClicked()
                }
            }
        )
    }
}

@Composable
fun SettingsOption(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = Color(0xFF165059),
            modifier = Modifier.size(24.dp)
        )

        Spacer(modifier = Modifier.width(12.dp))

        Text(
            text = label,
            fontSize = 16.sp,
            modifier = Modifier.weight(1f)
        )

        Icon(
            imageVector = Icons.Default.ArrowForward,
            contentDescription = "Siguiente",
            tint = Color.Gray
        )
    }
}
