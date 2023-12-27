package com.rickmasters.component.cameras.data.network.api

import com.rickmasters.common.core.BaseResponse
import com.rickmasters.component.cameras.data.network.model.GetCamerasResponse

internal interface CamerasApi {
    suspend fun getCameras(): BaseResponse<GetCamerasResponse>
}