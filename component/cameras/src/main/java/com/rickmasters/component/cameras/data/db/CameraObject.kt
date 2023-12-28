package com.rickmasters.component.cameras.data.db

import com.rickmasters.component.cameras.domain.Camera
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

internal class CameraObject : RealmObject {
    @PrimaryKey
    var id: String = ""
    var name: String = ""
    var snapshotUrl: String = ""
    var room: String? = null
    var favourite: Boolean = false
    var rec: Boolean = false
}

internal fun CameraObject.toDomain(): Camera {
    return Camera(
        id = id,
        name = name,
        snapshotUrl = snapshotUrl ,
        favourite = favourite,
        room = room,
        rec = rec
    )
}