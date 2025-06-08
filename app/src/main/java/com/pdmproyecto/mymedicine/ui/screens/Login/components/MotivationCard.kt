package com.pdmproyecto.mymedicine.ui.screens.Login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pdmproyecto.mymedicine.R

@Composable
fun MotivationCard(
    messageTop: String,
    messageBottom: String,
    onSearchClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp),
        shape = MaterialTheme.shapes.medium,
        elevation = androidx.compose.material3.CardDefaults.cardElevation(4.dp),
        colors = androidx.compose.material3.CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.climacaliente),
                    contentDescription = "Icono clima",
                    modifier = Modifier
                        .size(36.dp)
                        .padding(end = 12.dp),
                    contentScale = ContentScale.Fit
                )
                Column {
                    androidx.compose.material3.Text(
                        text = messageTop,
                        fontSize = 14.sp,
                        color = Color(0xFF18515A)
                    )
                    androidx.compose.material3.Text(
                        text = messageBottom,
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }
            }

            IconButton(
                onClick = onSearchClick,
                modifier = Modifier
                    .size(36.dp)
                    .clip(MaterialTheme.shapes.large)
                    .background(Color(0xFF18515A))
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Buscar",
                    tint = Color.White
                )
            }
        }
    }
}