package com.rickmasters.doors.ui.doors.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.rickmasters.common.ui.R
import com.rickmasters.common.ui.SwipeDirection
import com.rickmasters.common.ui.SwipeToReveal
import com.rickmasters.common.ui.theme.Yellow
import com.rickmasters.doors.ui.doors.model.ListElement

@Composable
internal fun DoorItem(
    model: ListElement.Door,
    onEditClick: () -> Unit,
    onFavouriteClick: () -> Unit,
    onLockClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    SwipeToReveal(
        swipeDirection = SwipeDirection.LEFT,
        underContent = {
            Row {
                IconButton(onClick = onEditClick) {
                    Image(
                        painter = painterResource(id = R.drawable.btn_edit),
                        contentDescription = null
                    )
                }

                IconButton(onClick = onFavouriteClick) {
                    Image(
                        painter = painterResource(id = R.drawable.btn_favourite),
                        contentDescription = null
                    )
                }
            }
        },
        aboveContent = {
            Surface(
                modifier = modifier,
                shape = MaterialTheme.shapes.medium,
                shadowElevation = 2.dp,
            ) {
                if (model.snapshotUrl != null) {
                    DoorItemWithPreview(
                        model = model,
                        onLockClick = onLockClick,
                    )
                } else {
                    DoorItemNoPreview(
                        model = model,
                        onLockClick = onLockClick,
                    )
                }
            }
        }
    )
}

@Composable
private fun DoorItemWithPreview(
    model: ListElement.Door,
    onLockClick: () -> Unit,
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Box {
            CameraPreview(imageUrl = model.snapshotUrl!!)

            if (model.favourite) {
                FavouriteIcon(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                )
            }
        }

        Row(
            modifier = Modifier
                .padding(start = 16.dp, top = 18.dp, bottom = 16.dp, end = 16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            DoorTitle(text = model.name)

            Spacer(modifier = Modifier.weight(1f))

            LockButton(locked = model.locked, onLockClick = onLockClick)
        }
    }
}

@Composable
private fun DoorItemNoPreview(
    model: ListElement.Door,
    onLockClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .padding(start = 16.dp, top = 18.dp, bottom = 16.dp, end = 16.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {

        DoorTitle(text = model.name)

        Spacer(modifier = Modifier.weight(1f))

        if (model.favourite) {
            FavouriteIcon()
        }

        LockButton(locked = model.locked, onLockClick = onLockClick)
    }
}

@Composable
private fun DoorTitle(
    text: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleSmall,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        modifier = modifier
    )
}

@Composable
fun FavouriteIcon(
    modifier: Modifier = Modifier,
) {
    Icon(
        imageVector = ImageVector.vectorResource(R.drawable.ic_star),
        contentDescription = null,
        tint = Yellow,
        modifier = modifier,
    )
}

@Composable
private fun LockButton(
    locked: Boolean,
    onLockClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    IconButton(onClick = onLockClick, modifier = modifier) {
        Icon(
            imageVector = ImageVector.vectorResource(
                if (locked) R.drawable.ic_lock else R.drawable.ic_lock_off
            ),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary
        )
    }
}