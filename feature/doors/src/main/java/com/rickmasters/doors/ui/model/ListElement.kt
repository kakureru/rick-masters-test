package com.rickmasters.doors.ui.model

import com.rickmasters.common.ui.UiText
import com.rickmasters.common.ui.asUiText
import com.rickmasters.component.doors.domain.Door
import com.rickmasters.doors.R

internal sealed interface ListElement {
    val id: String

    class Header(
        val text: UiText
    ) : ListElement {
        override val id = text.hashCode().toString()
    }

    class Door(
        override val id: String,
        val name: String,
        val favourite: Boolean,
        val snapshotUrl: String?,
        val locked: Boolean,
    ) : ListElement
}

internal fun List<Door>.toUi(): List<ListElement> {
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

    return buildList { addAll(withRoom + noRoom) }
}

internal fun Door.toUi() = ListElement.Door(
    id = id,
    name = name,
    favourite = favourite,
    snapshotUrl = snapshotUrl,
    locked = locked,
)