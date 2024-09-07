package com.theapplicationpad.moviebooking.Core

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.theapplicationpad.moviebooking.Model.nowPlayingMovie
import com.theapplicationpad.moviebooking.Presentation.DetailScreen
import com.theapplicationpad.moviebooking.Presentation.FoodScreen
import com.theapplicationpad.moviebooking.Presentation.HomeScreen
import com.theapplicationpad.moviebooking.Presentation.SeatSelectorScreen


object AppRoute {

    @Composable
    fun GenerateRoute(navController: NavHostController,isBoolean:Boolean,onModeToggle: () -> Unit) {
        NavHost(
            navController = navController,
            startDestination = AppRouteName.Home,
        ) {
            composable(AppRouteName.Home) {
                HomeScreen(navHostController = navController, ismymode = isBoolean, onModeToggle = onModeToggle)
            }
            composable("${AppRouteName.Detail}/{id}") { backStackEntry ->
                val id = backStackEntry.arguments?.getString("id")
                val movie = nowPlayingMovie.first{ it.id == id }

                DetailScreen(navController = navController, movieModel = movie)
            }
            composable(AppRouteName.SeatSelector) {
                SeatSelectorScreen(navController = navController)
            }
            composable(AppRouteName.Food) {
                FoodScreen(navController = navController)
            }
        }
    }
}