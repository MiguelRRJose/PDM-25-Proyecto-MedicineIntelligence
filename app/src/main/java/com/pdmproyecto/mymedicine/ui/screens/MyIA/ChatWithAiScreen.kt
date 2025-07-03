package com.pdmproyecto.mymedicine.ui.screens.MyIA

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.pdmproyecto.mymedicine.R
import com.pdmproyecto.mymedicine.ui.screens.MyIA.ChatWithAiViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatWithAiScreen(
    navController: NavHostController,
    viewModel: ChatWithAiViewModel = viewModel()
) {
    var userInput by remember { mutableStateOf("") }
    val aiResponse by viewModel.response.collectAsState()
    var isLoading by remember { mutableStateOf(false) }

    LaunchedEffect(aiResponse) {
        isLoading = false
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Asistente IA Médica") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(Color.White)
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(30.dp))

            Image(
                painter = painterResource(id = R.drawable.logo1),
                contentDescription = "Logo MI",
                modifier = Modifier.size(120.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Suéltame tus dolencias",
                fontSize = 20.sp,
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF165059)
            )

            Spacer(modifier = Modifier.height(40.dp))

            OutlinedTextField(
                value = userInput,
                onValueChange = { userInput = it },
                label = { Text("¿Qué te preocupa?") },
                placeholder = { Text("Por ejemplo: 'Me mareo con tal medicamento'") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF165059),
                    unfocusedBorderColor = Color.LightGray
                )
            )

            Button(
                onClick = {
                    if (userInput.isNotBlank()) {
                        isLoading = true
                        viewModel.sendMessage(userInput)
                        userInput = ""
                    }
                },
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(top = 8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF165059),
                    contentColor = Color.White
                )
            ) {
                Text("Enviar")
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Respuesta de la IA:",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(8.dp))

            if (isLoading) {
                CircularProgressIndicator(color = Color(0xFF165059))
            } else {
                val scrollState = rememberScrollState()
                val cleanResponse = aiResponse
                    .replace("**", "")
                    .replace("*", "")
                    .replace("\n", "\n\n") // para dar más espaciado entre líneas

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .verticalScroll(scrollState)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = if (aiResponse.isBlank())
                            "Esperando respuesta o la IA no respondió."
                        else
                            cleanResponse,
                        fontSize = 14.sp,
                        color = Color.DarkGray,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                }
            }
        }
    }
}
