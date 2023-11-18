package com.example.testpunchinandout


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

@Composable
fun TabletNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = "tablet_view_screen"
    ){
        composable("tablet_view_screen") {
            TabletViewScreen(navController = navController)
        }
        composable("tablet_punch_screen") {
            TabletPunchScreen(navController = navController)
        }


    }
}