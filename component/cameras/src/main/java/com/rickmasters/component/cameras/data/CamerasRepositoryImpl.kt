package com.rickmasters.component.cameras.data

import com.rickmasters.component.cameras.domain.Camera
import com.rickmasters.component.cameras.domain.CamerasRepository
import kotlinx.coroutines.flow.Flow

internal class CamerasRepositoryImpl(

) : CamerasRepository {



    override fun getCameras(): Flow<Result<List<Camera>>> {
        TODO("Not yet implemented")
    }

    override fun refreshCameras(): Flow<Result<Unit>> {
        TODO("Not yet implemented")
    }
}