package com.rickmasters.common.ui

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

sealed class UiText {
    data class RawString(val value: String) : UiText()

    class ResString(
        @StringRes val id: Int,
        val args: Array<Any> = emptyArray()
    ) : UiText()

    fun asString(context: Context): String {
        return when(this) {
            is RawString -> value
            is ResString -> context.resources.getString(id, args)
        }
    }

    @Composable
    fun asString(): String {
        return when(this) {
            is RawString -> value
            is ResString -> stringResource(id, args)
        }
    }
}

fun String.asUiText() = UiText.RawString(this)