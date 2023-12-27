package com.rickmasters.cameras.ui

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.rickmasters.cameras.R
import com.rickmasters.cameras.ui.model.CamerasEffect
import com.rickmasters.cameras.ui.model.CamerasScreenState
import com.rickmasters.cameras.ui.model.ListElement
import com.rickmasters.common.ui.FullscreenLoader
import com.rickmasters.common.ui.PlayOverlay
import com.rickmasters.common.ui.SwipeDirection
import com.rickmasters.common.ui.SwipeToReveal
import com.rickmasters.common.ui.theme.Yellow
import kotlinx.coroutines.flow.Flow
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
        effect = viewModel.uiEffect,
        modifier = modifier,
        onFavouriteClick = viewModel::onFavouriteClick
    )
}

@Composable
internal fun CamerasScreenLayout(
    state: CamerasScreenState,
    effect: Flow<CamerasEffect>,
    onFavouriteClick: (camId: String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        effect.collect {
            when (it) {
                is CamerasEffect.Error -> {
                    Toast.makeText(context, it.msg.asString(context), Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    when (state) {
        CamerasScreenState.Loading -> FullscreenLoader()

        is CamerasScreenState.Content -> {
            LazyColumn(
                modifier = modifier,
                verticalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(horizontal = 20.dp, vertical = 16.dp)
            ) {
                items(items = state.items, key = { item -> item.id }) {
                    when (it) {
                        is ListElement.Header -> Header(text = it.text.asString())

                        is ListElement.Camera -> CameraItem(
                            model = it,
                            onFavouriteClick = { onFavouriteClick(it.id) }
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
    SwipeToReveal(
        underContent = {
            IconButton(onClick = onFavouriteClick) {
                Image(
                    imageVector = ImageVector.vectorResource(CommonRes.drawable.btn_favourite),
                    contentDescription = null
                )
            }
        },
        aboveContent = {
            Surface(
                modifier = modifier,
                shape = MaterialTheme.shapes.medium,
                shadowElevation = 2.dp,
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Box {
                        CameraPreview(
                            rec = model.rec,
                            imageUrl = model.snapshotUrl
                        )

                        if (model.favourite) {
                            Icon(
                                imageVector = ImageVector.vectorResource(CommonRes.drawable.ic_star),
                                contentDescription = null,
                                tint = Yellow,
                                modifier = Modifier
                                    .align(Alignment.TopEnd)
                                    .padding(8.dp)
                            )
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
        },
        swipeDirection = SwipeDirection.LEFT
    )
}

@Composable
internal fun CameraPreview(
    rec: Boolean,
    imageUrl: String,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier.heightIn(max = 207.dp)) {
        AsyncImage(
            model = imageUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        if (rec) {
            Icon(
                imageVector = ImageVector.vectorResource(CommonRes.drawable.ic_rec),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(8.dp),
                tint = Color.Red
            )
        }

        PlayOverlay()
    }
}