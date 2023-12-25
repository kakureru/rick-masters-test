package com.rickmasters.cameras.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.rickmasters.common.ui.FullscreenLoader
import org.koin.androidx.compose.koinViewModel
import com.rickmasters.common.ui.R as CommonRes

@Composable
internal fun CamerasScreen(
    modifier: Modifier = Modifier,
    viewModel: CamerasViewModel = koinViewModel()
) {
    val state by viewModel.uiState.collectAsState()

    CamerasScreenLayout(
        state = state,
        modifier = modifier,
        onFavouriteClick = viewModel::onFavouriteClick
    )
}

@Composable
internal fun CamerasScreenLayout(
    state: CamerasScreenState,
    onFavouriteClick: (camId: Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    when (state) {
        CamerasScreenState.Loading -> {
            FullscreenLoader()
        }

        is CamerasScreenState.Content -> {
            LazyColumn(
                modifier = modifier,
                verticalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(horizontal = 20.dp)
            ) {
                items(items = state.elements, key = { item -> item.id }) {
                    when (it) {
                        is ListElement.Header -> Header(text = it.text)
                        is ListElement.Camera -> CameraItem(
                            model = it,
                            onFavouriteClick = { onFavouriteClick(it.camId) }
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
internal fun CameraItem(
    onFavouriteClick: () -> Unit,
    model: ListElement.Camera,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        shadowElevation = 5.dp,
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Box {
                CameraPreview(
                    rec = model.rec,
                    imageUrl = model.previewUrl
                )

                if (model.favourite) {
                    IconButton(
                        onClick = onFavouriteClick,
                        modifier = Modifier.align(Alignment.TopEnd)
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(CommonRes.drawable.ic_star),
                            contentDescription = null,
                        )
                    }
                }
            }

            Text(
                text = model.name,
                modifier = Modifier.padding(
                    start = 16.dp,
                    top = 22.dp,
                    bottom = 20.dp,
                    end = 16.dp
                ),
                style = MaterialTheme.typography.titleSmall
            )
        }
    }
}

@Composable
internal fun CameraPreview(
    rec: Boolean,
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

        if (rec) {
            Icon(
                imageVector = ImageVector.vectorResource(CommonRes.drawable.ic_rec),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(8.dp)
            )
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.2f))
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(CommonRes.drawable.play_button),
                contentDescription = null
            )
        }
    }
}