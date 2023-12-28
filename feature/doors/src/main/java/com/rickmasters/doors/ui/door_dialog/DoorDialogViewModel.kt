package com.rickmasters.doors.ui.door_dialog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rickmasters.common.ui.UiText
import com.rickmasters.common.utils.NoConnection
import com.rickmasters.common.utils.Result
import com.rickmasters.component.doors.domain.DoorsRepository
import com.rickmasters.doors.R
import com.rickmasters.common.ui.R as CommonRes
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class DoorDialogViewModel(
    private val doorId: String,
    private val doorsRepository: DoorsRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(DoorDialogState())
    val uiState: StateFlow<DoorDialogState> = _uiState.asStateFlow()

    private val _uiEffect = MutableSharedFlow<DoorDialogEffect>()
    val uiEffect: SharedFlow<DoorDialogEffect> = _uiEffect.asSharedFlow()

    init {
        viewModelScope.launch {
            when (val result = doorsRepository.getDoor(doorId)) {
                is Result.Success -> _uiState.update { it.copy(name = result.data.name) }
                is Result.Error -> _uiEffect.emit(DoorDialogEffect.Error(UiText.ResString(R.string.error_door_not_found)))
                is Result.Exception -> {
                    when (result.cause) {
                        is NoConnection -> _uiEffect.emit(DoorDialogEffect.Error(UiText.ResString(CommonRes.string.error_no_connection)))
                    }
                }
            }
        }
    }

    fun onNameChanged(text: String) {
        _uiState.update { it.copy(name = text) }
    }

    private fun renameRole(doorId: String) = viewModelScope.launch {
        doorsRepository.updateName(doorId = doorId, newName = uiState.value.name)
    }

    fun onConfirmClick() = viewModelScope.launch {
        if (isInputCorrect.not()) {
            _uiEffect.emit(DoorDialogEffect.Error(UiText.ResString(R.string.error_name_required)))
        } else {
            renameRole(doorId = doorId)
            _uiState.update { it.copy(navState = DoorDialogNavState.Dismiss) }
        }
    }

    private val isInputCorrect get() = uiState.value.name.isNotEmpty()
}