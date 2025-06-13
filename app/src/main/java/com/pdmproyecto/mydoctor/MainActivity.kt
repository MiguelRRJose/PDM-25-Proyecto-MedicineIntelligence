package com.pdmproyecto.mydoctor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pdmproyecto.mydoctor.ui.navigation.AppNavigation
import com.pdmproyecto.mydoctor.ui.theme.MyDoctorTheme
import com.pdmproyecto.mydoctor.ui.screens.Login.LoginPrincipal.DoctorLoginScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyDoctorTheme {
                val navController = rememberNavController()
                AppNavigation(navController)
            }
        }
    }
}

