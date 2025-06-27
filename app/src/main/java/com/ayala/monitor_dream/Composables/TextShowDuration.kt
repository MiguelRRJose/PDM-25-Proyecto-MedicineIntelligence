package com.ayala.monitor_dream.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable

fun TextDuration(hour: Int, minute: Int)
{
    Text(
        text = "🕒 Duración del sueño: " +
                "$hour h :" + "$minute min",
        color = Color.White,
        fontSize = 18.sp,
        modifier = Modifier.padding(bottom = 16.dp)
    )
}
