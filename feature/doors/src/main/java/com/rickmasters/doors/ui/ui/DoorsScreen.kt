package com.rickmasters.doors.ui.ui

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.rickmasters.common.ui.FullscreenLoader
import com.rickmasters.common.ui.PlayOverlay
import com.rickmasters.doors.ui.DoorsViewModel
import com.rickmasters.doors.ui.model.DoorsEffect
import com.rickmasters.doors.ui.model.DoorsScreenState
import com.rickmasters.doors.ui.model.ListElement
import kotlinx.coroutines.flow.Flow
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun DoorsScreen(
    modifier: Modifier = Modifier,
    viewModel: DoorsViewModel = koinViewModel(),
) {
    val state by viewModel.uiState.collectAsState()

    DoorsScreenLayout(
        state = state,
        effect = viewModel.uiEffect,
        onFavouriteClick = viewModel::onFavouriteClick,
        onLockClick = viewModel::onLockClick,
        modifier = modifier,
    )
}

@Composable
internal fun DoorsScreenLayout(
    state: DoorsScreenState,
    effect: Flow<DoorsEffect>,
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
                        is ListElement.Header -> Header(text = it.text.asString())
                        is ListElement.Door -> DoorItem(
                            model = it,
                            onLockClick = { onLockClick(it.id) }
                        )
                    }
                }
            }
        }
    }

    val context = LocalContext.current
    LaunchedEffect(Unit) {
        effect.collect {
            when (it) {
                is DoorsEffect.Error -> {
                    Toast.makeText(context, it.msg.asString(context), Toast.LENGTH_LONG).show()
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
internal fun CameraPreview(
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

        PlayOverlay()
    }
}