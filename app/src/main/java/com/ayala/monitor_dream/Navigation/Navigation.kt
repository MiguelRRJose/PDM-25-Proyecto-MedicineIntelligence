package com.ayala.monitor_dream.Navigation

sealed class Screen(val route: String) {

    object Alarm : Screen("alarm")
    object SleepTracking : Screen("sleep_tracking")

    object Sleep : Screen("sleep/{hour}/{minute}") {
        fun createRoute(hour: Int, minute: Int): String {
            return "sleep/$hour/$minute"
        }
    }
}
