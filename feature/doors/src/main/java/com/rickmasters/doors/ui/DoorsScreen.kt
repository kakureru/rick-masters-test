package com.rickmasters.doors.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.rickmasters.common.ui.FullscreenLoader
import com.rickmasters.common.ui.PlayOverlay
import com.rickmasters.common.ui.R
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun DoorsScreen(
    modifier: Modifier = Modifier,
    viewModel: DoorsViewModel = koinViewModel(),
) {
    val state by viewModel.uiState.collectAsState()

    DoorsScreenLayout(
        state = state,
        onFavouriteClick = viewModel::onFavouriteClick,
        onLockClick = viewModel::onLockClick,
        modifier = modifier,
    )
}

@Composable
internal fun DoorsScreenLayout(
    state: DoorsScreenState,
    onFavouriteClick: (doorId: String) -> Unit,
    onLockClick: (doorId: String) -> Unit,
    modifier: Modifier = Modifier,
) {
    when (state) {
        DoorsScreenState.Loading -> FullscreenLoader()

        is DoorsScreenState.Content -> {
            LazyColumn(
                modifier = modifier,
                verticalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(horizontal = 20.dp, vertical = 16.dp)
            ) {
                items(items = state.items, key = { item -> item.id }) {
                    when (it) {
                        is ListElement.Header -> Header(text = it.text)
                        is ListElement.Door -> DoorItem(
                            model = it,
                            onFavouriteClick = { onFavouriteClick(it.id) },
                            onLockClick = { onLockClick(it.id) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
internal fun Header(
    text: String,
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier,
        text = text,
        style = MaterialTheme.typography.titleMedium,
    )
}

@Composable
internal fun DoorItem(
    model: ListElement.Door,
    onFavouriteClick: () -> Unit,
    onLockClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        shadowElevation = 5.dp,
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Box {
                CameraPreview(imageUrl = model.snapshotUrl)

                if (model.favourite) {
                    IconButton(
                        onClick = onFavouriteClick,
                        modifier = Modifier.align(Alignment.TopEnd)
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.ic_star),
                            contentDescription = null,
                        )
                    }
                }
            }

            Row(
                modifier = Modifier.padding(
                    start = 16.dp,
                    top = 22.dp,
                    bottom = 20.dp,
                    end = 16.dp
                ),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = model.name,
                    style = MaterialTheme.typography.titleSmall
                )
                IconButton(onClick = onLockClick) {
                    Icon(
                        imageVector = ImageVector.vectorResource(
                            if (model.locked) R.drawable.ic_lock else R.drawable.ic_lock_off
                        ),
                        contentDescription = null
                    )
                }
            }
        }
    }
}

@Composable
internal fun CameraPreview(
    imageUrl: String,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        AsyncImage(
            model = imageUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth()
        )

        PlayOverlay()
    }
}