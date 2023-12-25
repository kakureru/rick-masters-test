package com.rickmasters.cameras.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

internal class CamerasViewModel(

) : ViewModel() {

    private val _uiState = MutableStateFlow(CamerasScreenState.Content())
    val uiState: StateFlow<CamerasScreenState> = _uiState.asStateFlow()

    fun onFavouriteClick(camId: Int) {

    }
}