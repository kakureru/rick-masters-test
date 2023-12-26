package com.rickmasters.component.doors.domain

import com.rickmasters.common.utils.Result
import kotlinx.coroutines.flow.Flow

interface DoorsRepository {
    fun getDoors(): Flow<Result<List<Door>>>
    fun refreshDoors(): Flow<Result<Unit>>
}