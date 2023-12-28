package com.rickmasters.component.cameras.data.network.model

import kotlinx.serialization.Serializable

@Serializable
internal class GetCamerasResponse(
    val cameras: List<CameraDto>
)