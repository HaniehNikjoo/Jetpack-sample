package ir.jetpack.challenge.view.ui

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import ir.jetpack.challenge.view.ui.theme.LocalDim
import ir.jetpack.challenge.view.ui.theme.Shapes

@Composable
fun ShimmerListItem(
    isLoading: Boolean,
    contentAfterLoading: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    if(isLoading) {
        Row(modifier = modifier.padding(vertical = LocalDim.current.spaceMedium)) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(Shapes.medium)
                    .shimmerEffect()
                    .padding(LocalDim.current.spaceNormal)
            )
            Spacer(modifier = Modifier.width(LocalDim.current.spaceMedium))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(LocalDim.current.spaceSLarge)
                        .padding(top = LocalDim.current.spaceSmall)
                        .shimmerEffect()
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(LocalDim.current.spaceSLarge)
                        .padding(top = LocalDim.current.spaceSmall)
                        .shimmerEffect()
                )
                Spacer(modifier = Modifier.height(12.dp))
                Row(Modifier.padding(top = LocalDim.current.spaceSLarge)) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.7f)
                            .height(LocalDim.current.spaceSLarge)
                            .shimmerEffect()
                    )
                    Spacer(modifier = Modifier.width(LocalDim.current.spaceMedium))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.7f)
                            .height(LocalDim.current.spaceSLarge)
                            .shimmerEffect()
                    )
                }
            }
        }
    } else {
        contentAfterLoading()
    }
}

fun Modifier.shimmerEffect(): Modifier = composed {
    var size by remember {
        mutableStateOf(IntSize.Zero)
    }
    val transition = rememberInfiniteTransition(label = "")
    val startOffsetX by transition.animateFloat(
        initialValue = -2 * size.width.toFloat(),
        targetValue = 2 * size.width.toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(1000)
        ), label = ""
    )

    background(
        brush = Brush.linearGradient(
            colors = listOf(
                Color(0xFFB8B5B5),
                Color(0xFF8F8B8B),
                Color(0xFFB8B5B5),
            ),
            start = Offset(startOffsetX, 0f),
            end = Offset(startOffsetX + size.width.toFloat(), size.height.toFloat())
        )
    )
        .onGloballyPositioned {
            size = it.size
        }
}