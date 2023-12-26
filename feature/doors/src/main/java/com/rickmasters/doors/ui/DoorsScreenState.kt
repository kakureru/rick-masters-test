package com.rickmasters.doors.ui

internal sealed interface DoorsScreenState {
    data object Loading : DoorsScreenState
    data class Content(
        val items: List<ListElement> = emptyList()
    ) : DoorsScreenState
}

internal sealed interface ListElement {
    val id: String

    class Header(
        val text: String
    ) : ListElement {
        override val id = text
    }

    class Door(
        override val id: String,
        val name: String,
        val favourite: Boolean,
        val snapshotUrl: String,
        val locked: Boolean,
    ) : ListElement
}