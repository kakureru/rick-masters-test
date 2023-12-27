package com.rickmasters.cameras.di

import com.rickmasters.cameras.ui.CamerasViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val featureCamerasModule = module {
    viewModel {
        CamerasViewModel(
            camerasRepository = get()
        )
    }
}