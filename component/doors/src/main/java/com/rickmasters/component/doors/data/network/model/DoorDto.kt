package com.rickmasters.component.doors.data.network.model

import com.rickmasters.component.doors.data.DoorObject
import kotlinx.serialization.Serializable

@Serializable
internal class DoorDto(
    val id: Int? = null,
    val name: String? = null,
    val snapshot: String? = null,
    val favorites: Boolean? = null,
    val room: String? = null,
)

internal fun DoorDto.toRealm(): DoorObject? {
    return DoorObject().apply {
        id = this@toRealm.id?.toString() ?: return null
        name = this@toRealm.name ?: return null
        snapshotUrl = this@toRealm.snapshot
        favorites = this@toRealm.favorites ?: return null
        room = this@toRealm.room
    }
}