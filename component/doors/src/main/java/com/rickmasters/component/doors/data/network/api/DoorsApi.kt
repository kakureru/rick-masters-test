package com.rickmasters.component.doors.data.network.api

import com.rickmasters.common.core.BaseResponse
import com.rickmasters.component.doors.data.network.model.DoorDto

internal interface DoorsApi {
    suspend fun getDoors(): BaseResponse<List<DoorDto>>
}