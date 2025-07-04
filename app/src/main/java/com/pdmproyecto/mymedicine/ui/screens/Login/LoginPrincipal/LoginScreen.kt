package com.pdmproyecto.mymedicine.ui.screens.Login.LoginPrincipal

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pdmproyecto.mymedicine.R
import com.pdmproyecto.mymedicine.ui.screens.Login.Modal.PatientRegisterModal
import androidx.navigation.NavController
import com.pdmproyecto.mymedicine.data.database.AppDatabase
import com.pdmproyecto.mymedicine.data.repositories.user.UserRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun LoginScreen(navController: NavController) {

    val context = LocalContext.current
    val db = AppDatabase.getDatabase(context)
    val userRepository = UserRepository(db.UserDao())

    val viewModel: LoginViewModel = viewModel(
        factory = LoginViewModelFactory(userRepository)
    )

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var rememberMe by remember { mutableStateOf(false) }
    val openModal = remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    var isLoading by remember { mutableStateOf(false) }



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
            text = "Tu salud, más inteligente",
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
                label = { Text("Correo Electrónico") },
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
                .padding(horizontal = 30.dp), // un poco más contenido
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = rememberMe,
                onCheckedChange = { rememberMe = it }
            )
            Text("Recuérdame", modifier = Modifier.weight(1f), )
            TextButton(onClick = { /* TODO */ }) {
                Text("Olvidé mi contraseña", fontSize = 12.sp)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                viewModel.login(email, password) { success ->
                    if (success) {
                        val username = viewModel.loggedInUser?.name ?: "Usuario"
                        navController.navigate("dashboard/$username")
                    }
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF165059)),
            modifier = Modifier
                .width(350.dp)
                .height(48.dp),
            enabled = !isLoading
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    color = Color.White,
                    modifier = Modifier.size(24.dp),
                    strokeWidth = 2.dp
                )
            } else {
                Text("Iniciar Sesión", color = Color.White)
            }
        }


        Spacer(modifier = Modifier.height(24.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
        ) {
            Divider(modifier = Modifier.weight(1f))
            Text(
                text = "o",
                modifier = Modifier.padding(horizontal = 8.dp),
                fontSize = 14.sp
            )
            Divider(modifier = Modifier.weight(1f))
        }


        Spacer(modifier = Modifier.height(16.dp))

        OutlinedButton(
            onClick = { /* TODO: login con Google */ },
            modifier = Modifier
                .width(350.dp)
                .height(48.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.google_1),
                contentDescription = "Google logo",
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text("Iniciar con Google")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("¿Aún no tienes cuenta?")
            TextButton(onClick = { openModal.value = true }) {
                Text(
                    text = "Registrate",
                    textDecoration = TextDecoration.Underline,
                    color = Color(0xFF165059),
                    fontWeight = FontWeight.Medium
                )

            }
            if (openModal.value) {
                PatientRegisterModal(onDismiss = { openModal.value = false })
            }
        }
    }
}
