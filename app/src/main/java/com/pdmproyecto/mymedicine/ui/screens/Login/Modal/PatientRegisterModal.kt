package com.pdmproyecto.mymedicine.ui.screens.Login.Modal


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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pdmproyecto.mymedicine.ui.screens.Login.Sucess.PatientRegisterSuccessModal


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PatientRegisterModal(
    onDismiss: () -> Unit,
    viewModel: PatientRegisterViewModel = viewModel()
) {
    val dui = viewModel.dui
    val email = viewModel.email
    val age = viewModel.age
    val phone = viewModel.phone
    val password = viewModel.password
    val username = viewModel.username
    val showSuccess = viewModel.showSuccess

    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        dragHandle = { BottomSheetDefaults.DragHandle() },
        containerColor = Color.White,
        tonalElevation = 8.dp
    ) {
        Column(

            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 16.dp)
        ) {
            FieldLabel("Ingrese su número de DUI")
            RegisterTextField(dui) { viewModel.dui = it }

            FieldLabel("Correo Electrónico")
            RegisterTextField(email) { viewModel.email = it }

            FieldLabel("Edad")
            RegisterTextField(age) { viewModel.age = it }

            FieldLabel("Teléfono de contacto")
            RegisterTextField(phone) { viewModel.phone = it }

            FieldLabel("Contraseña")
            RegisterTextField(password) { viewModel.password = it }

            FieldLabel("Nombre de usuario")
            RegisterTextField(username) { viewModel.username = it }

            Spacer(modifier = Modifier.height(22.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Button(
                    onClick = { viewModel.onRegisterClick() },
                    modifier = Modifier
                        .width(180.dp)
                        .height(45.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF165059))
                ) {
                    Text(text = "Registrarse")
                }
            }
        }

        if (showSuccess) {
            PatientRegisterSuccessModal(
                onDismiss = {
                    viewModel.dismissSuccessModal()
                    onDismiss()
                }
            )
        }
    }
}

@Composable
fun FieldLabel(text: String) {
    Text(
        text = text,
        textAlign = TextAlign.Center,
        fontSize = 12.sp,
        color = Color.Gray,
        modifier = Modifier
            .padding(start = 8.dp, top = 8.dp, bottom = 4.dp)
            .fillMaxWidth(0.85f)
    )
}

@Composable
fun RegisterTextField(value: String, onValueChange: (String) -> Unit) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .width(320.dp)
            .height(50.dp),
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color(0xFF1A5B59).copy(alpha = 0.85f),
            focusedContainerColor = Color(0xFF1A5B59).copy(alpha = 0.85f),
            unfocusedTextColor = Color.White,
            focusedTextColor = Color.White,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent
        ),
        textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
        shape = RoundedCornerShape(50.dp),
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
    )
}

