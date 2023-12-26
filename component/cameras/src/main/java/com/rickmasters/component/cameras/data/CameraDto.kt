package com.rickmasters.component.cameras.data

import com.rickmasters.component.cameras.domain.Camera
import kotlinx.serialization.Serializable

@Serializable
internal class CameraDto(
    val id: String? = null,
    val name: String? = null,
    val snapshot: String? = null,
    val favourites: Boolean? = null,
    val room: String? = null,
    val rec: Boolean? = null,
)

internal fun CameraDto.toDomain(): Camera? {
    return Camera(
        id = id ?: return null,
        name = name ?: return null,
        snapshotUrl = snapshot,
        favourite = favourites ?: return null,
        room = room,
        rec = rec ?: return null
    )
}