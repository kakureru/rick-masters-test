package com.rickmasters.component.doors.di

import com.rickmasters.component.doors.data.db.DoorObject
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import org.koin.dsl.module

internal val internalModule = module {

    single {
        val config = RealmConfiguration.Builder(
            schema = setOf(DoorObject::class)
        )
            .compactOnLaunch()
            .build()
        Realm.open(config)
    }
}