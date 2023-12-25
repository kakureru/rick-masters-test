package com.rickmasters.app.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rickmasters.cameras.navigation.CamerasNavHost

@Composable
fun RootNavHost(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(navController = navController, startDestination = "1") {
        composable(route = "1") {
            CamerasNavHost()
        }
    }
}