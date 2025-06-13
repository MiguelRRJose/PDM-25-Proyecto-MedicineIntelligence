package com.pdmproyecto.mydoctor.ui.screens.Login.LoginPrincipal

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pdmproyecto.mydoctor.R
import com.pdmproyecto.mydoctor.ui.screens.Login.Modal.DoctorRegisterModal
import androidx.navigation.NavController

@Composable
fun DoctorLoginScreen(navController: NavController, viewModel: DoctorLoginViewModel = remember { DoctorLoginViewModel() }) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var rememberMe by remember { mutableStateOf(false) }
    val openModal = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(30.dp))

        Image(
            painter = painterResource(id = R.drawable.logo1),
            contentDescription = "Logo MI",
            modifier = Modifier.size(235.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Control médico inteligente",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(32.dp))

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Numero de empleado") },
                leadingIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.correoelectronico),
                        contentDescription = "Icono correo",
                        modifier = Modifier.size(24.dp)
                    )
                },
                modifier = Modifier
                    .width(350.dp)
                    .height(64.dp),
                shape = RoundedCornerShape(20.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Contraseña") },
                leadingIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.bloquear),
                        contentDescription = "Icono candado",
                        modifier = Modifier.size(24.dp)
                    )
                },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                modifier = Modifier
                    .width(350.dp)
                    .height(64.dp),
                shape = RoundedCornerShape(20.dp)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = rememberMe,
                onCheckedChange = { rememberMe = it }
            )
            Text("Recuérdame", modifier = Modifier.weight(1f))
            TextButton(onClick = { /* TODO */ }) {
                Text("Olvidé mi contraseña", fontSize = 12.sp)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { navController.navigate("doctor_dashboard") },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF165059)),
            modifier = Modifier
                .width(350.dp)
                .height(48.dp)
        ) {
            Text("Iniciar Sesión", color = Color.White)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("¿Aún no tienes cuenta?")
            TextButton(onClick = { openModal.value = true }) {
                Text(
                    text = "Registrarse como doctor",
                    textDecoration = TextDecoration.Underline,
                    color = Color(0xFF165059),
                    fontWeight = FontWeight.Medium
                )
            }

            if (openModal.value) {
                DoctorRegisterModal(onDismiss = { openModal.value = false })
            }
        }
    }
}
