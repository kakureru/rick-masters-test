package com.rickmasters.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.rickmasters.common.ui.theme.RickmasterstestTheme
import com.rickmasters.myhome.navigation.MyHomeNavHost
import org.koin.compose.KoinContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KoinContext {
                RickmasterstestTheme {
                    MyHomeNavHost()
                }
            }
        }
    }
}