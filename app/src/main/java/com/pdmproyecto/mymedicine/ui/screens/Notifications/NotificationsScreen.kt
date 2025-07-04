package com.pdmproyecto.mymedicine.ui.screens.Notification

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.pdmproyecto.mymedicine.ui.screens.Notifications.Notification
import com.pdmproyecto.mymedicine.ui.screens.Notifications.NotificationViewModel

@Composable
fun NotificationScreen(
    navController: NavHostController,
    viewModel: NotificationViewModel = viewModel()
) {
    val notifications by remember { derivedStateOf { viewModel.notifications } }
    val groupedNotifications = notifications.groupBy { it.dateLabel }

    Column(modifier = Modifier.fillMaxSize()) {

        // HEADER CON FLECHA Y TÍTULO CENTRADO
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 40.dp, bottom = 12.dp, start = 12.dp, end = 12.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Notificaciones",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF18515A)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Volver",
                        tint = Color(0xFF18515A)
                    )
                }
                TextButton(onClick = { viewModel.markAllAsRead() }) {
                    Text("Mark all", color = Color.Black)
                }
            }
        }

        // LISTA DE NOTIFICACIONES AGRUPADAS
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            groupedNotifications.forEach { (dateLabel, group) ->
                item {
                    if (!dateLabel.isNullOrBlank()) {
                        Text(
                            text = dateLabel,
                            modifier = Modifier
                                .padding(start = 16.dp, top = 12.dp, bottom = 4.dp)
                                .background(Color(0xFFE0E0E0), RoundedCornerShape(12.dp))
                                .padding(horizontal = 12.dp, vertical = 4.dp),
                            color = Color.Black,
                            fontSize = 14.sp
                        )
                    }
                }

                items(group, key = { it.id }) { notification ->
                    NotificationItem(
                        title = notification.title,
                        message = notification.message,
                        time = notification.time,
                        highlighted = notification.isActive,
                        onClick = { viewModel.activateNotification(notification.id) }
                    )
                }
            }
        }
    }
}

@Composable
fun NotificationItem(
    title: String,
    message: String,
    time: String,
    highlighted: Boolean,
    onClick: () -> Unit
) {
    val backgroundColor = if (highlighted) Color(0xFFD3E2E3) else Color.White

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(backgroundColor)
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF18515A)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = "Calendario",
                    tint = Color.White,
                    modifier = Modifier.size(20.dp)
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp,
                    color = Color.Black
                )
                Text(
                    text = message,
                    fontSize = 12.sp,
                    color = Color.Gray,
                    maxLines = 1
                )
            }

            Text(text = time, fontSize = 12.sp, color = Color.Gray)
        }
    }
}
