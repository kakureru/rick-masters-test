package com.rickmasters.cameras.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.rickmasters.cameras.ui.CamerasScreen

@Composable
fun CamerasNavHost(
    modifier: Modifier = Modifier,
) {
    CamerasScreen(modifier = modifier)
}