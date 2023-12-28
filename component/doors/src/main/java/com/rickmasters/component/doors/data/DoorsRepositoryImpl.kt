package com.rickmasters.component.doors.data

import com.rickmasters.component.doors.data.network.api.DoorsApi
import com.rickmasters.component.doors.domain.Door
import com.rickmasters.component.doors.domain.DoorsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import com.rickmasters.common.utils.Result
import com.rickmasters.common.utils.runRequestCatchingNonCancellation
import com.rickmasters.component.doors.data.network.model.toRealm
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import kotlinx.coroutines.flow.first

internal class DoorsRepositoryImpl(
    private val doorsApi: DoorsApi,
    private val realm: Realm,
) : DoorsRepository {

    override fun getDoors(): Flow<Result<List<Door>>> = flow {
        val cache = realm.query<DoorObject>().asFlow().first().list

        if (cache.isEmpty()) {
            refreshDoors()
        }

        realm.query<DoorObject>().asFlow().collect { dbData ->
            emit(Result.Success(dbData.list.map { it.toDomain() }))
        }
    }

    override suspend fun refreshDoors() {
        val result = runRequestCatchingNonCancellation {
            doorsApi.getDoors().data.mapNotNull { it.toRealm() }
        }
        if (result is Result.Success) {
            realm.write {
                result.data.map { copyToRealm(it) }
            }
        }
    }

    override suspend fun toggleFavourite(doorId: String) {
        realm.write {
            val queriedDoor = query<DoorObject>("id == $0", doorId).first().find()
            queriedDoor?.apply {
                favorites =  !favorites
            }
        }
    }

    override suspend fun toggleLock(doorId: String) {
        realm.write {
            val queriedDoor = query<DoorObject>("id == $0", doorId).first().find()
            queriedDoor?.apply {
                locked = !locked
            }
        }
    }
}