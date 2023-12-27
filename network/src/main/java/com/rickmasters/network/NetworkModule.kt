package com.rickmasters.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logging
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val networkModule = module {
    single {
        HttpClient(Android) {
            install(Logging) { level = LogLevel.ALL }
            install(JsonFeature) {
                serializer = KotlinxSerializer(
                    json = Json {
                        ignoreUnknownKeys = true
                    }
                )
            }
        }
    }
}