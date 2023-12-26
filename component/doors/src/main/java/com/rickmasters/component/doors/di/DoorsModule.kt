package com.rickmasters.component.doors.di

import com.rickmasters.component.doors.data.DoorsRepositoryImpl
import com.rickmasters.component.doors.data.network.api.DoorsApi
import com.rickmasters.component.doors.data.network.api.DoorsApiImpl
import com.rickmasters.component.doors.domain.DoorsRepository
import org.koin.dsl.module

val componentDoorsModule = module {
    single<DoorsRepository> {
        DoorsRepositoryImpl(
            doorsApi = get()
        )
    }

    single<DoorsApi> {
        DoorsApiImpl(
            client = get()
        )
    }
}