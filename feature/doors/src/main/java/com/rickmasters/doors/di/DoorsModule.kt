package com.rickmasters.doors.di

import com.rickmasters.doors.ui.DoorsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val featureDoorsModule = module {
    viewModel {
        DoorsViewModel(
            doorsRepository = get()
        )
    }
}