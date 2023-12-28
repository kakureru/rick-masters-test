package com.rickmasters.component.doors.data

import com.rickmasters.component.doors.domain.Door
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

internal class DoorObject : RealmObject {
    @PrimaryKey
    var id: String = ""
    var name: String = ""
    var snapshotUrl: String? = null
    var favorites: Boolean = false
    var room: String? = null
}

internal fun DoorObject.toDomain() = Door(
    id = id,
    name = name,
    snapshotUrl = snapshotUrl,
    favourite = favorites,
    room = room,
)