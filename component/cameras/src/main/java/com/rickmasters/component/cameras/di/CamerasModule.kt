package com.rickmasters.component.cameras.di

import com.rickmasters.component.cameras.data.CamerasRepositoryImpl
import com.rickmasters.component.cameras.data.network.api.CamerasApi
import com.rickmasters.component.cameras.data.network.api.CamerasApiImpl
import com.rickmasters.component.cameras.domain.CamerasRepository
import org.koin.dsl.module

val componentCamerasModule = module {

    single<CamerasRepository> {
        CamerasRepositoryImpl(
            camerasApi = get()
        )
    }

    single<CamerasApi> {
        CamerasApiImpl(
            client = get()
        )
    }
}