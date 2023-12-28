package com.rickmasters.doors.navigation

internal sealed class DoorsRoute(val route: String) {
    data object Doors: DoorsRoute(route = "doors")
    data object DoorDialog: DoorsRoute(route = "door_dialog") {
        const val ARG_DOOR_ID = "ARG_DOOR_ID"
    }
}