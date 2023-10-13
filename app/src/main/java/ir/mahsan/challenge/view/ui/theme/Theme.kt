package ir.mahsan.challenge.view.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.Typography
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class ColorPalette(
    val primary: Color = MainColor,
    val primaryVariant: Color = Primary,
    val background: Color = Background,
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

private val DarkColorPalette = darkColors(
    primary = Color(0x0D6EFDEE),
    primaryVariant = Color(0xEEEEEEEE),
    background = Color.Black,
)

private val LightColorPalette = lightColors(
    primary = Color(0x0D6EFDEE),
    primaryVariant = Color(0xEEEEEEEE),
    background = Color.White,
)

val isDarkMode by mutableStateOf(false)
val LocalIsDarkMode = compositionLocalOf { isDarkMode }

@Composable
fun SystemTheme(isDarkTheme: Boolean = isDarkMode, content: @Composable () -> Unit) {
    val colors = if (isDarkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    CompositionLocalProvider(
        LocalIsDarkMode provides isDarkTheme
    ) {
        MaterialTheme(
            colors = colors,
            typography = Typography,
            shapes = Shapes,
            content = content
        )
    }
}

