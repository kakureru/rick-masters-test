package com.rickmasters.cameras.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rickmasters.cameras.ui.model.CamerasEffect
import com.rickmasters.cameras.ui.model.CamerasScreenState
import com.rickmasters.cameras.ui.model.toUi
import com.rickmasters.common.ui.R
import com.rickmasters.common.ui.UiText
import com.rickmasters.common.ui.asUiText
import com.rickmasters.common.utils.NoConnection
import com.rickmasters.common.utils.Result
import com.rickmasters.component.cameras.domain.CamerasRepository
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

internal class CamerasViewModel(
    private val camerasRepository: CamerasRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow<CamerasScreenState>(CamerasScreenState.Content())
    val uiState: StateFlow<CamerasScreenState> = _uiState.asStateFlow()

    private val _uiEffect = MutableSharedFlow<CamerasEffect>(replay = 1)
    val uiEffect: SharedFlow<CamerasEffect> = _uiEffect.asSharedFlow()

    init {
        loadCameras()
    }

    private fun loadCameras() {
        camerasRepository.getCameras()
            .onStart {
                _uiState.value = CamerasScreenState.Loading
            }
            .onEach { result ->
                when (result) {
                    is Result.Success -> {
                        _uiState.value = CamerasScreenState.Content(items = result.data.toUi())
                    }
                    is Result.Error -> {
                        _uiEffect.emit(
                            CamerasEffect.Error(
                            result.message?.asUiText() ?: UiText.ResString(R.string.error_unknown)
                        ))
                    }
                    is Result.Exception -> {
                        when (result.cause) {
                            NoConnection -> _uiEffect.emit(CamerasEffect.Error(UiText.ResString(R.string.error_no_connection)))
                        }
                    }
                }
            }
            .flowOn(Dispatchers.IO)
            .launchIn(viewModelScope)
    }

    fun onFavouriteClick(camId: String) {

    }
}