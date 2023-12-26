package com.rickmasters.doors.ui.model

import com.rickmasters.common.ui.UiText

internal sealed interface DoorsScreenState {
    data object Loading : DoorsScreenState
    data class Content(
        val refreshing: Boolean = false,
        val items: List<ListElement> = emptyList()
    ) : DoorsScreenState
}

internal sealed interface DoorsEffect {
    class Error(val msg: UiText) : DoorsEffect
}