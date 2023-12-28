package com.rickmasters.component.cameras.data.network.model

import com.rickmasters.component.cameras.data.CameraObject
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

internal fun CameraDto.toRealm(): CameraObject? {
    return CameraObject().apply {
        id = this@toRealm.id?.toString() ?: return null
        name = this@toRealm.name ?: return null
        snapshotUrl = this@toRealm.snapshot ?: return null
        favourite = this@toRealm.favorites ?: return null
        room = this@toRealm.room
        rec = this@toRealm.rec ?: return null
    }
}