package com.ayala.monitor_dream.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource

@Composable
fun PersonalBackground(id : Int, description : String)
{
    Image(painter = painterResource(id = id),
        contentDescription = description,
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
    )

}
