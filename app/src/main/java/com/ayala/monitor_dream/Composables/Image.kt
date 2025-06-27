package com.ayala.monitor_dream.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun SelectedImage (id : Int, description : String)
{
    Image(
        painter = painterResource(id = id),
        contentDescription = description ,
        modifier = Modifier.size(160.dp)
    )
}