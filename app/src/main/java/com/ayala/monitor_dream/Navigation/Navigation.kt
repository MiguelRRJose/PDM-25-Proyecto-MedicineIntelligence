package com.ayala.monitor_dream.Navigation

sealed class Screen(val route: String) {
    object Sleep : Screen("sleep_screen")
    object Alarm : Screen("alarm_screen")
}
