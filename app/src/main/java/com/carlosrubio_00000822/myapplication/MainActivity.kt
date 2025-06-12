package com.carlosrubio_00000822.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.carlosrubio_00000822.myapplication.ui.screen.WaterIntakeScreen   // <-- Importamos tu pantalla
import com.carlosrubio_00000822.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // En lugar de Greeting(), llamamos a WaterIntakeScreen
                    WaterIntakeScreen(
                        drank = 4,
                        goal = 8,
                        percentage = 0.77f,
                        onDrinkClick = { /* aquí tu lógica al pulsar “Bebiendo” */ },
                        onBackPressed = { finish() },
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

