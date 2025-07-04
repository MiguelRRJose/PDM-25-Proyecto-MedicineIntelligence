package com.pdmproyecto.mymedicine.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.pdmproyecto.mymedicine.ui.theme.DarkGreen

@Composable
fun ConfirmationPopUp(text: String, isVisible: Boolean = false, confirmAction: () -> Unit, cancelAction: () -> Unit){

    val animationDuration = 100
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ){

        AnimatedVisibility(
            visible = isVisible,
            enter = fadeIn(animationSpec = tween(durationMillis = animationDuration)),
            exit = fadeOut(animationSpec = tween(durationMillis = animationDuration))
        ){
            Box(
                modifier = Modifier
                    .background(color = Color.Black.copy(alpha = 0.7f))
                    .fillMaxSize()
                    .clickable(onClick = {}),
            ){}
        }


        AnimatedVisibility(
            visible = isVisible,
            enter = scaleIn(animationSpec = tween(durationMillis = animationDuration)),
            exit = scaleOut(animationSpec = tween(durationMillis = animationDuration))
        ) {
            Column(modifier = Modifier
                .background(color = Color.White, shape = RoundedCornerShape(20 .dp))
                .padding(10.dp)
                .height(100.dp)
                .fillMaxWidth(0.75f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ){
                Text(
                    modifier = Modifier.weight(0.6f),
                    text = text,
                    textAlign = TextAlign.Center,
                    style = LocalTextStyle.current.copy(textAlign = TextAlign.Center, color = Color.Black),
                    )

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




    }

}