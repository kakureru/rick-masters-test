package com.rickmasters.component.doors.data

import com.rickmasters.component.doors.data.network.api.DoorsApi
import com.rickmasters.component.doors.data.network.model.toDomain
import com.rickmasters.component.doors.domain.Door
import com.rickmasters.component.doors.domain.DoorsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import com.rickmasters.common.utils.Result
import com.rickmasters.common.utils.runRequestCatchingNonCancellation
import kotlinx.coroutines.delay

internal class DoorsRepositoryImpl(
    private val doorsApi: DoorsApi,
) : DoorsRepository {
    override fun getDoors(): Flow<Result<List<Door>>> = flow {
        val result = runRequestCatchingNonCancellation {
            doorsApi.getDoors().data.mapNotNull { it.toDomain() }
        }
        emit(result)
    }

    override fun refreshDoors(): Flow<Result<Unit>> {
        TODO("Not yet implemented")
    }
}