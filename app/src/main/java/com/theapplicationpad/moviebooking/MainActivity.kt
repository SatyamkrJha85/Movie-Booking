package com.theapplicationpad.moviebooking

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.theapplicationpad.moviebooking.Core.AppRoute
import com.theapplicationpad.moviebooking.ui.theme.MovieBookingTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val systemUiController = rememberSystemUiController()
            val modechange = remember{
                mutableStateOf(false)
            }
            systemUiController.setStatusBarColor(
                color = Color.Transparent,
                darkIcons = true,
            )
            systemUiController.setNavigationBarColor(
                color = Color.Transparent,
                darkIcons = true,
            )

            MovieBookingTheme(darkTheme = modechange.value) {
                Scaffold {paddingValues ->
                    Column(Modifier.padding(paddingValues)) {
                        /// main navigation
                        val navController = rememberNavController()
                        AppRoute.GenerateRoute(navController = navController, isBoolean = modechange.value,onModeToggle = { modechange.value = !modechange.value })
                    }
                }


            }
        }
    }
}

