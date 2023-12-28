package com.rickmasters.component.doors.domain

class Door(
    val id: String,
    val name: String,
    val snapshotUrl: String?,
    val favourite: Boolean,
    val room: String?,
    val locked: Boolean,
)