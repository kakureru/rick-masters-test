package com.rickmasters.component.cameras.data

import com.rickmasters.common.utils.Result
import com.rickmasters.common.utils.runRequestCatchingNonCancellation
import com.rickmasters.component.cameras.data.db.CameraObject
import com.rickmasters.component.cameras.data.db.toDomain
import com.rickmasters.component.cameras.data.network.api.CamerasApi
import com.rickmasters.component.cameras.data.network.model.toRealm
import com.rickmasters.component.cameras.domain.Camera
import com.rickmasters.component.cameras.domain.CamerasRepository
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

internal class CamerasRepositoryImpl(
    private val camerasApi: CamerasApi,
    private val realm: Realm,
) : CamerasRepository {
    override suspend fun toggleFavourite(camId: String) {
        realm.write {
            val queriedCam = query<CameraObject>("id == $0", camId).first().find()
            queriedCam?.apply {
                favourite =  !favourite
            }
        }
    }

    override fun getCameras(): Flow<Result<List<Camera>>> = flow {
        val cache = realm.query<CameraObject>().asFlow().first().list

        if (cache.isEmpty()) {
            refreshCameras()
        }

        realm.query<CameraObject>().asFlow().collect { dbData ->
            emit(Result.Success(dbData.list.map { it.toDomain() }))
        }
    }

    override suspend fun refreshCameras() {
        val result = runRequestCatchingNonCancellation {
            camerasApi.getCameras().data.cameras.mapNotNull { it.toRealm() }
        }
        if (result is Result.Success) {
            realm.write {
                val cache = query<CameraObject>().find()
                delete(cache)
                result.data.map { copyToRealm(it) }
            }
        }
    }
}