package com.carlosrubio_00000822.myapplication.ui.navigation

sealed class Screen(val route: String) {
    object Water : Screen("water")
    object Steps : Screen("steps")
    object History : Screen("history")
}
