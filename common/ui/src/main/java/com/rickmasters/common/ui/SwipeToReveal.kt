package com.rickmasters.common.ui

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.gestures.DraggableAnchors
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.anchoredDraggable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

enum class SwipeDirection {
    LEFT, RIGHT
}

internal enum class SwipeAnchors(val fraction: Float) {
    IDLE(0f),
    REVEALED(1f),
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SwipeToReveal(
    modifier: Modifier = Modifier,
    underContent: @Composable () -> Unit,
    aboveContent: @Composable () -> Unit,
    swipeDirection: SwipeDirection,
    velocityThreshold: Dp = 100.dp,
    positionalThreshold: Float = 0.5f,
    animationSpec: AnimationSpec<Float> = tween(200)
) {
    val density = LocalDensity.current
    val dragState = remember {
        AnchoredDraggableState(
            initialValue = SwipeAnchors.IDLE,
            positionalThreshold = { distance: Float -> distance * positionalThreshold },
            velocityThreshold = { with(density) { velocityThreshold.toPx() } },
            animationSpec = animationSpec,
        )
    }
    val padding = with(density) { 8.dp.toPx() }
    val underAlignment = if (swipeDirection == SwipeDirection.LEFT) Alignment.CenterEnd else Alignment.CenterStart
    val direction = if (swipeDirection == SwipeDirection.LEFT) -1 else 1

    Box(
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .align(underAlignment)
                .onSizeChanged { layoutSize ->
                    val dragEndPoint = layoutSize.width + padding
                    dragState.updateAnchors(
                        DraggableAnchors {
                            SwipeAnchors.entries.forEach { it at dragEndPoint * it.fraction * direction }
                        }
                    )
                },
        ) {
            underContent()
        }

        Box(
            modifier = Modifier
                .offset { IntOffset(x = dragState.requireOffset().roundToInt(), y = 0) }
                .anchoredDraggable(dragState, Orientation.Horizontal)
        ) {
            aboveContent()
        }
    }
}