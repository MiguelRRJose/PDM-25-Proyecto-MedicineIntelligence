package com.pdmproyecto.mymedicine.Composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun TimeCard(label: String, timeText: String, id : Int ,onClick: () -> Unit) {

    Card(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .clickable {onClick()},
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onSecondary,
            contentColor = Color.Black)
    ) {

        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier.size(20.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.onBackground)

            )
            Spacer(modifier = Modifier.width(5.dp))

            Text(text = label, color = Color.Black)
            Text(
                text = timeText,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun CardIcon(id: Int, text: String,color: Color)
{
    Row(modifier = Modifier.padding(vertical = 8.dp))
    {
        TextEspecialPersColr(text, color)

        Icon(
            painter = painterResource(id = id),
            contentDescription = null,
            tint = Color.Unspecified,
            modifier = Modifier.size(20.dp)
        )

    }
}
