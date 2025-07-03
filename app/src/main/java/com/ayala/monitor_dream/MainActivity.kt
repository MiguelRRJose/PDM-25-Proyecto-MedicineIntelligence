package com.ayala.monitor_dream

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.navigation.compose.rememberNavController
import com.ayala.monitor_dream.navigation.AlarmP
import com.ayala.monitor_dream.navigation.AppNavigator
import com.ayala.monitor_dream.navigation.SleepY


class MainActivity : ComponentActivity() {

    private val navigationTarget = mutableStateOf<Any?>(null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        handleIntent(intent)

        setContent {

            val navController = rememberNavController()

            AppNavigator(navController)

            val target = navigationTarget.value

            LaunchedEffect(target){
                if (target!=null) {

                    Log.d("Notificacion!!", "Notificación recibida")
                    try {
                        navController.navigate(target)
                    } catch (e: IllegalArgumentException) {
                            Log.d("Notificacion!!!!", "Notificación recibida")
                        }

                    navigationTarget.value = null

                }
            }
        }
    }
    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        if(true){
        handleIntent(intent)}
    }
    private fun handleIntent(intent: Intent) {

        val routeExtra ="NAVIGATION_TO_SLEEP_Y_SCREEN"

        if (intent.hasExtra(routeExtra)) {

            if (intent.getBooleanExtra(routeExtra, false)){
                navigationTarget.value = AlarmP

            }
        }
    }
}
