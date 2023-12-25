package com.rickmasters.cameras.ui

internal sealed interface CamerasScreenState {
    data object Loading : CamerasScreenState
    data class Content(
        val elements: List<ListElement> = emptyList()
    ) : CamerasScreenState
}

internal sealed interface ListElement {
    val id: String

    data class Header(
        val text: String
    ) : ListElement {
        override val id = text
    }

    data class Camera(
        val camId: Int,
        val name: String,
        val favourite: Boolean,
        val previewUrl: String,
        val rec: Boolean,
    ) : ListElement {
        override val id = camId.toString()
    }
}