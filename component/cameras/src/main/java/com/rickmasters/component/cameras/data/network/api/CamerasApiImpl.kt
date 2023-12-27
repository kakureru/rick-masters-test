package com.rickmasters.component.cameras.data.network.api

import com.rickmasters.common.core.BaseResponse
import com.rickmasters.component.cameras.data.network.model.GetCamerasResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.url

internal class CamerasApiImpl(
    private val client: HttpClient,
) : CamerasApi {
    override suspend fun getCameras(): BaseResponse<GetCamerasResponse> {
        return client.get { url("http://cars.cprogroup.ru/api/rubetek/cameras/ ") }
    }
}