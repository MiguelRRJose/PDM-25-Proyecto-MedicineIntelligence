package com.ayala.monitor_dream.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
        text = "Meta para dormir " + "    "+
                "$hour h : " + "$minute min",
        color = Color.Black,
        fontSize = 16.sp,
        modifier = Modifier
            .background(
                color = Color.White,
                shape = RoundedCornerShape(percent = 50)
            )
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .padding(bottom = 8.dp)
            .padding(top = 8.dp)
    )
}
