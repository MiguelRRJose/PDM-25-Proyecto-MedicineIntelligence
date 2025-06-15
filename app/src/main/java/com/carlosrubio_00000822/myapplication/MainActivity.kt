package com.carlosrubio_00000822.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.carlosrubio_00000822.myapplication.ui.theme.MyApplicationTheme
import com.carlosrubio_00000822.myapplication.ui.navigation.AppNavGraph

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                AppNavGraph()
            }
        }
    }
}

