package com.rickmasters.component.cameras.di

import com.rickmasters.component.cameras.data.db.CameraObject
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import org.koin.dsl.module

internal val internalModule = module {

    single {
        val config = RealmConfiguration.Builder(
            schema = setOf(CameraObject::class)
        )
            .compactOnLaunch()
            .build()
        Realm.open(config)
    }
}