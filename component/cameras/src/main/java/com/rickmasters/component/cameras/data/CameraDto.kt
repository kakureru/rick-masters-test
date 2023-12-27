package com.rickmasters.component.cameras.data

import com.rickmasters.component.cameras.domain.Camera
import kotlinx.serialization.Serializable

@Serializable
internal data class CameraDto(
    val id: Int? = null,
    val name: String? = null,
    val snapshot: String? = null,
    val favorites: Boolean? = null,
    val room: String? = null,
    val rec: Boolean? = null,
)

internal fun CameraDto.toDomain(): Camera? {
    return Camera(
        id = id?.toString() ?: return null,
        name = name ?: return null,
        snapshotUrl = snapshot ?: return null,
        favourite = favorites ?: return null,
        room = room,
        rec = rec ?: return null
    )
}