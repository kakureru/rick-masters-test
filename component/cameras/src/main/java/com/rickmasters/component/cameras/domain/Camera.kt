package com.rickmasters.component.cameras.domain

class Camera(
    val id: String,
    val name: String,
    val snapshotUrl: String?,
    val room: String?,
    val favourite: Boolean,
    val rec: Boolean,
)