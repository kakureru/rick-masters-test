package com.rickmasters.doors.di

import com.rickmasters.doors.ui.doors.DoorsViewModel
import com.rickmasters.doors.ui.door_dialog.DoorDialogViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val featureDoorsModule = module {
    viewModel {
        DoorsViewModel(
            doorsRepository = get()
        )
    }

    viewModel {
        DoorDialogViewModel(
            doorId = it.get(),
            doorsRepository = get()
        )
    }
}