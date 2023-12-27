package com.rickmasters.cameras.ui.model

import com.rickmasters.common.ui.UiText

internal sealed interface CamerasScreenState {
    data object Loading : CamerasScreenState
    data class Content(
        val refreshing: Boolean = false,
        val items: List<ListElement> = emptyList()
    ) : CamerasScreenState
}

internal sealed interface CamerasEffect {
    class Error(val msg: UiText) : CamerasEffect
}