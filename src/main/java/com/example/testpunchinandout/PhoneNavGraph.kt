package com.example.testpunchinandout

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.testpunchinandout.screens.PhonePunchScreen
import com.example.testpunchinandout.screens.PhoneViewScreen

// navigation when app is used with phone

@Composable
fun PhoneNavGraph(navController: NavHostController) {

    val sharedViewModel: SharedViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = "phone_view_screen"
    ) {
        composable("phone_view_screen") {
            PhoneViewScreen(navController = navController, sharedViewModel = sharedViewModel)
        }
        composable(
            "phone_punch_screen/{email}",
            arguments = listOf(
                navArgument("email") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val email = backStackEntry.arguments?.getString("email") ?: ""
            PhonePunchScreen(navController = navController, email = email, sharedViewModel = sharedViewModel)
        }
    }
}