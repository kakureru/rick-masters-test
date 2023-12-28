package com.rickmasters.doors.ui.door_dialog

import com.rickmasters.common.ui.UiText

internal data class DoorDialogState(
    val name: String = "",
    val navState: DoorDialogNavState = DoorDialogNavState.Idle,
)

internal sealed class DoorDialogNavState {
    data object Idle : DoorDialogNavState()
    data object Dismiss : DoorDialogNavState()
}

internal sealed class DoorDialogEffect {
    class Error(val msg: UiText) : DoorDialogEffect()
}