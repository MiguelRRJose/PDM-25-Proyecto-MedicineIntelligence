package com.pdmproyecto.mymedicine.screens.Settings.ChangePassword

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.shape.RoundedCornerShape
import kotlinx.coroutines.launch

@Composable
fun ChangePasswordScreen(
    onBackClick: () -> Unit,
    viewModel: ChangePasswordViewModel
) {
    val scope = rememberCoroutineScope()

    val currentPassword by viewModel.currentPassword.collectAsState()
    val newPassword by viewModel.newPassword.collectAsState()
    val confirmPassword by viewModel.confirmPassword.collectAsState()

    var showCurrent by remember { mutableStateOf(false) }
    var showNew by remember { mutableStateOf(false) }
    var showConfirm by remember { mutableStateOf(false) }

    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is ChangePasswordViewModel.UiEvent.ShowError -> {
                    snackbarHostState.showSnackbar(event.message)
                }
                is ChangePasswordViewModel.UiEvent.PasswordChanged -> {
                    snackbarHostState.showSnackbar("Contraseña cambiada exitosamente")
                }
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(horizontal = 24.dp)
                .background(Color.White)
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Volver",
                        tint = Color(0xFF165059)
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = "Cambiar Contraseña",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF165059)
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            PasswordField(
                label = "Contraseña Actual",
                value = currentPassword,
                onValueChange = viewModel::onCurrentPasswordChanged,
                visible = showCurrent,
                onVisibilityChange = { showCurrent = !showCurrent },
                showForgot = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            PasswordField(
                label = "Nueva Contraseña",
                value = newPassword,
                onValueChange = viewModel::onNewPasswordChanged,
                visible = showNew,
                onVisibilityChange = { showNew = !showNew }
            )

            Spacer(modifier = Modifier.height(16.dp))

            PasswordField(
                label = "Confirmar Nueva Contraseña",
                value = confirmPassword,
                onValueChange = viewModel::onConfirmPasswordChanged,
                visible = showConfirm,
                onVisibilityChange = { showConfirm = !showConfirm }
            )

            Spacer(modifier = Modifier.height(40.dp))

            Button(
                onClick = {
                    scope.launch { viewModel.onChangePasswordClicked() }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(30.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF165059))
            ) {
                Text("Cambiar Contraseña", color = Color.White)
            }
        }
    }
}

@Composable
fun PasswordField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    visible: Boolean,
    onVisibilityChange: () -> Unit,
    showForgot: Boolean = false
) {
    Column {
        Text(
            text = label,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF165059)
        )
        Spacer(modifier = Modifier.height(4.dp))

        TextField(
            value = value,
            onValueChange = onValueChange,
            visualTransformation = if (visible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                IconButton(onClick = onVisibilityChange) {
                    Icon(
                        imageVector = if (visible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                        contentDescription = null,
                        tint = Color(0xFF165059)
                    )
                }
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(0xFFD1DDDF),
                unfocusedContainerColor = Color(0xFFD1DDDF),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                cursorColor = Color.Black,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black
            ),
            shape = RoundedCornerShape(15.dp),
            modifier = Modifier.fillMaxWidth()
        )

        if (showForgot) {
            Text(
                text = "¿Olvidaste Tu Contraseña?",
                fontSize = 12.sp,
                color = Color(0xFF165059),
                modifier = Modifier
                    .padding(top = 4.dp)
                    .align(Alignment.End)
            )
        }
    }
}
