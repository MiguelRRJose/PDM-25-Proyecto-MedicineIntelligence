package com.carlosrubio_00000822.myapplication

import android.Manifest
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.carlosrubio_00000822.myapplication.ui.theme.MyApplicationTheme
import com.carlosrubio_00000822.myapplication.ui.navigation.AppNavGraph
import android.content.pm.PackageManager
import android.widget.Toast

class MainActivity : ComponentActivity() {

    // 1) Prepara el launcher para pedir el permiso
    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (!isGranted) {
                Toast.makeText(
                    this,
                    "Sin permiso, el contador de pasos no funcionará",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            when {
                ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACTIVITY_RECOGNITION
                ) == PackageManager.PERMISSION_GRANTED -> {
                }
                else -> {
                    requestPermissionLauncher.launch(Manifest.permission.ACTIVITY_RECOGNITION)
                }
            }
        }

        setContent {
            MyApplicationTheme {
                AppNavGraph()
            }
        }
    }
}
