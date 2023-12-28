package com.rickmasters.component.doors.domain

import com.rickmasters.common.utils.Result
import kotlinx.coroutines.flow.Flow

interface DoorsRepository {
    fun getDoors(): Flow<Result<List<Door>>>
    suspend fun refreshDoors()
    suspend fun toggleFavourite(doorId: String)
    suspend fun toggleLock(doorId: String)
}