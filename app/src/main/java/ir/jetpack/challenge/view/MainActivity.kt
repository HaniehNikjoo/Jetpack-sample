package ir.jetpack.challenge.view

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import ir.jetpack.challenge.view.ui.SetupNavGraph
import ir.jetpack.challenge.view.ui.theme.GeneralTheme
import ir.jetpack.challenge.view.ui.theme.SystemTheme

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Lock orientation to portrait
        @SuppressLint("SourceLockedOrientationActivity")
        requestedOrientation =
            ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        setContent {
            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
                SystemTheme {
                    Surface(
                        color = GeneralTheme.colors.background, modifier = Modifier.fillMaxSize()
                    ) {
                        val navController = rememberNavController()
                        SetupNavGraph(navController)
                    }
                }
            }
        }
    }
}