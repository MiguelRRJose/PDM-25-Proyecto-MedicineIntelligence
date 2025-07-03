package com.pdmproyecto.mymedicine.ui.screens.History

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import com.pdmproyecto.mymedicine.R

@Composable
fun HistoryScreen(
    items: List<MedicineHistory>,
    onDelete: (MedicineHistory) -> Unit,
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(top = 32.dp)
    ) {
        // Flecha de volver
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBackPressed) {
                Icon(
                    Icons.Filled.ArrowBack,
                    contentDescription = "Volver",
                    tint = Color.Black
                )
            }
        }

        Spacer(Modifier.height(8.dp))

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
                // Nombre y dosis
                Text(
                    text = "${item.name} ${item.dosage}",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )
                Spacer(Modifier.height(4.dp))
                // Fecha y hora (azul oscuro)
                Text(
                    text = "Tomado: ${item.dateTime}",
                    fontSize = 14.sp,
                    color = Color(0xFF115C5C)
                )
                Spacer(Modifier.height(2.dp))
                // Frecuencia (azul oscuro)
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
                    Icons.Filled.Delete,
                    contentDescription = "Eliminar",
                    tint = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}
