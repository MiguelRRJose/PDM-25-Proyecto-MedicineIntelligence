package com.pdmproyecto.mydoctor.ui.screens.Login.Sucess

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pdmproyecto.mydoctor.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DoctorRegisterSuccessModal(
    viewModel: DoctorRegisterSuccessViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
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
            Text(text = "¡Bienvenido, Doctor!", fontSize = 20.sp)

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
