package com.pdmproyecto.mymedicine.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.pdmproyecto.mymedicine.ui.theme.DarkGreen

@Composable
fun ConfirmationPopUp(text: String, confirmAction: () -> Unit, cancelAction: () -> Unit){
    Column(modifier = Modifier
        .background(color = Color.White, shape = RoundedCornerShape(15.dp))
        .padding(10.dp)
        .height(100.dp)
        .fillMaxWidth(0.75f),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ){
        Text(
            modifier = Modifier.weight(0.6f),
            text = text)

        Row (
            modifier = Modifier
                .weight(0.4f)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {

            Button(
                content = @Composable{Text(text = "Cancelar", fontStyle = FontStyle.Italic, fontWeight = FontWeight.Bold)},
                onClick = {cancelAction()},
                colors = ButtonColors(containerColor = Color.Red, contentColor = Color.White, disabledContentColor = Color.White, disabledContainerColor = Color.Gray)
            )

            Button(
                content = @Composable{Text(text = "Aceptar", fontStyle = FontStyle.Italic, fontWeight = FontWeight.Bold)},
                onClick = {confirmAction()},
                colors = ButtonColors(containerColor = DarkGreen, contentColor = Color.White, disabledContentColor = Color.White, disabledContainerColor = Color.Gray)
            )
        }
    }
}