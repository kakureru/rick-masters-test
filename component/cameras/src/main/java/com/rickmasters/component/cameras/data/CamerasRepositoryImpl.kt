package com.rickmasters.component.cameras.data

import com.rickmasters.common.utils.Result
import com.rickmasters.common.utils.runRequestCatchingNonCancellation
import com.rickmasters.component.cameras.data.network.api.CamerasApi
import com.rickmasters.component.cameras.domain.Camera
import com.rickmasters.component.cameras.domain.CamerasRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class CamerasRepositoryImpl(
    private val camerasApi: CamerasApi,
) : CamerasRepository {

    override fun getCameras(): Flow<Result<List<Camera>>> = flow {
        val result = runRequestCatchingNonCancellation {
            camerasApi.getCameras().data.cameras.mapNotNull { it.toDomain() }
        }
        emit(result)
    }

    override fun refreshCameras(): Flow<Result<Unit>> {
        TODO("Not yet implemented")
    }
}