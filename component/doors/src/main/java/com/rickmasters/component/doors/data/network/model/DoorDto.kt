package com.rickmasters.component.doors.data.network.model

import com.rickmasters.component.doors.domain.Door
import kotlinx.serialization.Serializable

@Serializable
internal class DoorDto(
    val id: Int? = null,
    val name: String? = null,
    val snapshot: String? = null,
    val favorites: Boolean? = null,
    val room: String? = null,
)

internal fun DoorDto.toDomain(): Door? {
    return Door(
        id = id?.toString() ?: return null,
        name = name ?: return null,
        snapshotUrl = snapshot,
        favourite = favorites ?: return null,
        room = room,
    )
}