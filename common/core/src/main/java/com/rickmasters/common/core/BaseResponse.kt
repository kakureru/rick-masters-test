package com.rickmasters.common.core

import kotlinx.serialization.Serializable

@Serializable
class BaseResponse<T>(
    val success: Boolean? = null,
    val data: T
)