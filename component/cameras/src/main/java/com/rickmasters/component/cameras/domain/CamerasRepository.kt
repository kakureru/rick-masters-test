package com.rickmasters.component.cameras.domain

import kotlinx.coroutines.flow.Flow
import com.rickmasters.common.utils.Result

interface CamerasRepository {
    fun getCameras(): Flow<Result<List<Camera>>>
    suspend fun refreshCameras()
    suspend fun toggleFavourite(camId: String)
}