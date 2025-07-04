package com.pdmproyecto.mymedicine.ui.screens.Login.Sucess

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pdmproyecto.mymedicine.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PatientRegisterSuccessModal(
    viewModel: PatientRegisterSuccessViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    onDismiss: () -> Unit
) {
    val username = viewModel.username

    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        dragHandle = { BottomSheetDefaults.DragHandle() },
        containerColor = Color.White,
        tonalElevation = 8.dp
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
        ) {
            Text(text = "¡Bienvenido!", fontSize = 20.sp)

            Spacer(modifier = Modifier.height(16.dp))

            Image(
                painter = painterResource(id = R.drawable.usuario),
                contentDescription = "Usuario",
                modifier = Modifier.size(80.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(text = username, fontSize = 18.sp, color = Color.Gray)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Se ha registrado correctamente", fontSize = 14.sp, color = Color.Gray)

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    viewModel.resetUsername()
                    onDismiss()
                },
                modifier = Modifier
                    .width(180.dp)
                    .height(45.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF165059))
            ) {
                Text(text = "Continuar")
            }
        }
    }
}

