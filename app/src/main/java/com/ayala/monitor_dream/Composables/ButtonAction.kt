package com.ayala.monitor_dream.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable

fun ButtonAction(action: String, modifier: Modifier = Modifier, onClick: () -> Unit)
{
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(20.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3A5A9A),
            contentColor = Color.White),
        modifier = modifier
    ) {
        TextSub(action)
    }
}

@Composable

fun ButtonSleepScreen(action: String, onClick: () -> Unit)
{
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(20.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3A5A9A),
            contentColor = Color.White),
        modifier = Modifier.
        fillMaxWidth().
        padding(horizontal = 110.dp)
    ) {
        TextSub(action)
    }
}


