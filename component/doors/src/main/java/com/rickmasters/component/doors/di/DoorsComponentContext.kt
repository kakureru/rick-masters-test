package com.rickmasters.component.doors.di

import org.koin.dsl.koinApplication

internal object DoorsComponentContext {

    private val koinApp by lazy {
        koinApplication {
            modules(internalModule)
        }
    }

    val koin get() = koinApp.koin
}