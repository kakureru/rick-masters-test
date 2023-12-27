package com.rickmasters.cameras.ui.model

import android.util.Log
import com.rickmasters.cameras.R
import com.rickmasters.common.ui.UiText
import com.rickmasters.common.ui.asUiText
import com.rickmasters.component.cameras.domain.Camera

internal sealed interface ListElement {
    val id: String

    data class Header(
        val text: UiText
    ) : ListElement {
        override val id = text.hashCode().toString()
    }

    data class Camera(
        override val id: String,
        val name: String,
        val favourite: Boolean,
        val snapshotUrl: String,
        val rec: Boolean,
    ) : ListElement
}

internal fun List<Camera>.toUi(): List<ListElement> {
    val groups = groupBy { it.room }
    val withRoom = groups.filter { it.key != null }.flatMap { entry ->
        buildList {
            add(ListElement.Header(entry.key!!.asUiText()))
            addAll(entry.value.map { it.toUi() })
        }
    }
    val noRoom = groups[null]?.map { it.toUi() }?.let {
        buildList {
            add(ListElement.Header(UiText.ResString(R.string.other)))
            addAll(it)
        }
    } ?: emptyList()

    Log.d("MYTAG", withRoom.toString())
    Log.d("MYTAG", noRoom.toString())

    return buildList { addAll(withRoom + noRoom) }
}

internal fun Camera.toUi() = ListElement.Camera(
    id = id,
    name = name,
    favourite = favourite,
    snapshotUrl = snapshotUrl,
    rec = rec,
)