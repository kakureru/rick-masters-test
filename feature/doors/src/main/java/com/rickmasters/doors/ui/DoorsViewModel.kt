package com.rickmasters.doors.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

internal class DoorsViewModel(

) : ViewModel() {

    private val _uiState = MutableStateFlow<DoorsScreenState>(DoorsScreenState.Content())
    val uiState: StateFlow<DoorsScreenState> = _uiState.asStateFlow()

    fun onFavouriteClick(doorId: String) {

    }

    fun onLockClick(doorId: String) {

    }
}