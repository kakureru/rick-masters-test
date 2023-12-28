package com.rickmasters.component.cameras.di

import org.koin.dsl.koinApplication

internal object CamerasComponentContext {

    private val koinApp by lazy {
        koinApplication {
            modules(internalModule)
        }
    }

    val koin get() = koinApp.koin
}