package com.carlosrubio_00000822.myapplication.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.carlosrubio_00000822.myapplication.ui.screen.HistoryScreen
import com.carlosrubio_00000822.myapplication.ui.screen.WaterIntakeScreen
import com.carlosrubio_00000822.myapplication.ui.screen.StepCounterScreen
import com.carlosrubio_00000822.myapplication.ui.viewmodel.HistoryViewModel
import com.carlosrubio_00000822.myapplication.ui.viewmodel.StepCounterViewModel
import com.carlosrubio_00000822.myapplication.ui.viewmodel.WaterIntakeViewModel

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = Screen.History.route) {
        composable(Screen.Water.route) {
            val vm: WaterIntakeViewModel = viewModel()
            WaterIntakeScreen(
                drank        = vm.drank,
                goal         = vm.goal,
                percentage   = vm.percentage,
                onDrinkClick = { vm.onDrink() },
                onViewSteps  = { navController.navigate(Screen.Steps.route) }
            )
        }

        composable(Screen.Steps.route) {
            val vm: StepCounterViewModel = viewModel()
            StepCounterScreen(
                steps         = vm.steps,
                goalSteps     = vm.goalSteps,
                onBackPressed = { navController.popBackStack() }
            )
        }

        composable(Screen.History.route) {
            val vm: HistoryViewModel = viewModel()
            // Convierte el StateFlow en List antes de pasarlo:
            val historyList by vm.historyList.collectAsState()

            HistoryScreen(
                items         = historyList,
                onDelete      = { vm.deleteItem(it) },
                onBackPressed = { navController.popBackStack() }
            )
        }
    }
}
