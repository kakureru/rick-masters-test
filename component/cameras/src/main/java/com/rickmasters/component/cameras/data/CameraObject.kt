package com.rickmasters.component.cameras.data

import com.rickmasters.component.cameras.domain.Camera
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

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