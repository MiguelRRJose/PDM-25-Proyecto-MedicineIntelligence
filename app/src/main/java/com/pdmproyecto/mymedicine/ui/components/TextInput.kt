package com.pdmproyecto.mymedicine.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign

@Composable
fun TextInput(
    modifier: Modifier,
    title: String = "",
    label: String = "",
    value: String,
    enabled: Boolean = true,
    onChange: (String) -> Unit = {},
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default
){

    val componentColor = {
        if (enabled) Color.Black
        else Color.LightGray
    }

    Column (
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            label = {Text(text = label)},
            value = value,
            onValueChange = onChange,
            enabled = enabled,
            singleLine = true,
            keyboardOptions = keyboardOptions,
            textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center, color = Color.Black),
        )

        if (title.isNotBlank()){
            Text(text = title, color = componentColor())
        }

    }

}