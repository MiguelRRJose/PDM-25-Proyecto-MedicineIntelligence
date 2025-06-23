package com.ayala.monitor_dream.Navigation

sealed class Screen(val route: String) {
    object Alarm : Screen("alarm")
    object Sleep : Screen("sleep")
    object SleepTracking : Screen("sleep_tracking")
}
