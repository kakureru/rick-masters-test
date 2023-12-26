package com.rickmasters.component.cameras.domain

import kotlinx.coroutines.flow.Flow

interface CamerasRepository {
    fun getCameras(): Flow<Result<List<Camera>>>
    fun refreshCameras(): Flow<Result<Unit>>
}