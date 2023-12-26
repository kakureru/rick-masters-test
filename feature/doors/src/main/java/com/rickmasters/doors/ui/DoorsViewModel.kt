package com.rickmasters.doors.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rickmasters.common.ui.R
import com.rickmasters.common.ui.UiText
import com.rickmasters.common.ui.asUiText
import com.rickmasters.common.utils.NoConnection
import com.rickmasters.common.utils.Result
import com.rickmasters.component.doors.domain.DoorsRepository
import com.rickmasters.doors.ui.model.DoorsEffect
import com.rickmasters.doors.ui.model.DoorsScreenState
import com.rickmasters.doors.ui.model.toUi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart

internal class DoorsViewModel(
    private val doorsRepository: DoorsRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow<DoorsScreenState>(DoorsScreenState.Content())
    val uiState: StateFlow<DoorsScreenState> = _uiState.asStateFlow()

    private val _uiEffect = MutableSharedFlow<DoorsEffect>(replay = 1)
    val uiEffect: SharedFlow<DoorsEffect> = _uiEffect.asSharedFlow()

    init {
        loadDoors()
    }

    private fun loadDoors() {
        doorsRepository.getDoors()
            .onStart {
                _uiState.value = DoorsScreenState.Loading
            }
            .onEach { result ->
                when (result) {
                    is Result.Success -> {
                        _uiState.value = DoorsScreenState.Content(items = result.data.toUi())
                    }
                    is Result.Error -> {
                        _uiEffect.emit(DoorsEffect.Error(
                            result.message?.asUiText() ?: UiText.ResString(R.string.error_unknown)
                        ))
                    }
                    is Result.Exception -> {
                        when (result.cause) {
                            NoConnection -> _uiEffect.emit(DoorsEffect.Error(UiText.ResString(R.string.error_no_connection)))
                        }
                    }
                }
            }
            .flowOn(Dispatchers.IO)
            .launchIn(viewModelScope)
    }

    fun onFavouriteClick(doorId: String) {

    }

    fun onLockClick(doorId: String) {

    }
}