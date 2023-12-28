package com.rickmasters.doors.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.rickmasters.doors.ui.door_dialog.DoorDialog
import com.rickmasters.doors.ui.doors.ui.DoorsScreen
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun DoorsNavHost(
    modifier: Modifier = Modifier,
) {
    val navHostController = rememberNavController()
    NavHost(navController = navHostController, startDestination = DoorsRoute.Doors.route) {

        composable(route = DoorsRoute.Doors.route) {
            DoorsScreen(
                modifier = modifier,
                onEditClick = { doorId ->
                    with(DoorsRoute.DoorDialog) {
                        navHostController.navigate("$route/$doorId")
                    }
                }
            )
        }

        with(DoorsRoute.DoorDialog) {
            dialog(
                route = "$route/{$ARG_DOOR_ID}",
                arguments = listOf(
                    navArgument(ARG_DOOR_ID) {
                        type = NavType.StringType
                    }
                ),
                dialogProperties = DialogProperties(usePlatformDefaultWidth = false)
            ) { entry ->
                Box(modifier = Modifier.fillMaxSize()) { // to fix bug with weird animation when dialog appear off screen
                    DoorDialog(
                        onDismissRequest = { navHostController.popBackStack() },
                        viewModel = koinViewModel { parametersOf(entry.arguments?.getString(ARG_DOOR_ID)) }
                    )
                }
            }
        }
    }
}