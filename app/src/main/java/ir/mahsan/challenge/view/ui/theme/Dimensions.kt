package ir.mahsan.challenge.view.ui.theme

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

val LocalDim = compositionLocalOf { Dimensions() }
data class Dimensions(
    val spaceSmall: Dp = 5.dp,
    val spaceMedium: Dp = 15.dp,
    val spaceNormal: Dp = 10.dp,
    val spaceLarge: Dp = 20.dp,
    val spaceSLarge: Dp = 25.dp,
    val spaceXLarge: Dp = 120.dp,
)
