package ir.mahsan.challenge.view.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class ColorPalette(
    val primary: Color = MainColor,
    val primaryVariant: Color = Primary,
    val background: Color = Background,
    val secondBackground: Color = SecondBackground,
    val title: Color = Color.White,
    val text: Color = Color.LightGray,
)

val LocalReplaceColorPalette = staticCompositionLocalOf {
    ColorPalette()
}
object MahsanTheme {
    val colors: ColorPalette
        @Composable
        get() = LocalReplaceColorPalette.current

    val typography: Typography
        @Composable
        get() = MaterialTheme.typography

    val shapes: Shapes
        @Composable
        get() = MaterialTheme.shapes
}

@Composable
fun SystemTheme(content: @Composable () -> Unit) {
    val replacementColor = ColorPalette()
    CompositionLocalProvider(LocalReplaceColorPalette provides replacementColor) {
        MaterialTheme(
            typography = Typography,
            shapes = Shapes,
            content = content
        )
    }
}