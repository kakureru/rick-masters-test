package com.rickmasters.common.utils

import java.io.IOException
import kotlin.coroutines.cancellation.CancellationException

sealed class Result<T> {
    class Success<T>(val data: T) : Result<T>()
    class Error<T>(val message: String?, data: T? = null) : Result<T>()
    class Exception<T>(val cause: Cause): Result<T>() {
        interface Cause
    }
}

object NoConnection : Result.Exception.Cause

inline fun <T> runRequestCatchingNonCancellation(block: () -> T): Result<T> {
    return try {
        Result.Success(block())
    } catch(e: IOException) {
        Result.Exception(NoConnection)
    } catch (e: CancellationException) {
        throw e
    } catch(e: Exception) {
        e.printStackTrace()
        Result.Error(e.message)
    }
}