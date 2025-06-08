package com.pdmproyecto.mymedicine

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.pdmproyecto.mymedicine.ui.screens.Login.LoginPrincipal.LoginScreen
import com.pdmproyecto.mymedicine.ui.theme.MyMedicineTheme
import androidx.navigation.compose.rememberNavController
import com.pdmproyecto.mymedicine.ui.navigation.AppNavigation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyMedicineTheme {
                val navController = rememberNavController()
                AppNavigation(navController)
            }
        }
    }
}
