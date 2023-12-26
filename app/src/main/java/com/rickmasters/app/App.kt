package com.rickmasters.app

import android.app.Application
import com.rickmasters.cameras.di.featureCamerasModule
import com.rickmasters.component.cameras.di.componentCamerasModule
import com.rickmasters.component.doors.di.componentDoorsModule
import com.rickmasters.doors.di.featureDoorsModule
import com.rickmasters.network.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@App)
            modules(
                featureCamerasModule,
                featureDoorsModule,
                componentCamerasModule,
                componentDoorsModule,
                networkModule,
            )
        }
    }
}