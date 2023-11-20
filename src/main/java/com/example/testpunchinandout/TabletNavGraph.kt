package com.example.testpunchinandout


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.testpunchinandout.screens.TabletPunchScreen

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