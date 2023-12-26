package com.rickmasters.component.doors.data.network.api

import com.rickmasters.common.core.BaseResponse
import com.rickmasters.component.doors.data.network.model.DoorDto
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.url

internal class DoorsApiImpl(
    private val client: HttpClient,
) : DoorsApi {
    override suspend fun getDoors(): BaseResponse<List<DoorDto>> {
        return client.get { url("http://cars.cprogroup.ru/api/rubetek/doors") }
    }
}