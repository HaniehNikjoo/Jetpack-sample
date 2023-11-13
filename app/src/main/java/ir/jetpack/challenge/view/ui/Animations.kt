package ir.jetpack.challenge.view.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateInt
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import ir.jetpack.challenge.R
import ir.jetpack.challenge.view.ui.theme.GeneralTheme

@Composable
fun Animations(navController: NavHostController) {
    Column(
        modifier = Modifier
            .testTag("NewsList")
            .fillMaxSize()
            .background(Color.DarkGray)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        Header(title = stringResource(R.string.animations))

        var isVisible by remember {
            mutableStateOf(false)
        }
        Button(onClick = {
            isVisible = !isVisible
        }) {
            Text(text = "Visibility")
        }
        AnimatedVisibility(
            visible = isVisible,
            enter = slideInHorizontally() + fadeIn(),
            exit = slideOutHorizontally() + fadeOut(),
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            Box(
                modifier = Modifier
                    .background(GeneralTheme.colors.primary)
                    .size(100.dp)
            )
        }

        var isRound by remember {
            mutableStateOf(false)
        }
        val borderRadius by animateIntAsState(
            targetValue = if (isRound) 100 else 0,
            label = "",
            animationSpec = tween(
                durationMillis = 3000,
                delayMillis = 500
            )
        )
        val size by animateIntAsState(
            targetValue = if (isRound) 50 else 100,
            label = "",
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioHighBouncy,
                stiffness = Spring.StiffnessVeryLow
            )
        )
        Button(onClick = {
            isRound = !isRound
        }) {
            Text(text = "State")
        }
        Row {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(borderRadius))
                    .background(GeneralTheme.colors.primary)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Box(
                modifier = Modifier
                    .size(size.dp)
                    .clip(RoundedCornerShape(borderRadius))
                    .background(GeneralTheme.colors.primary)
            )
        }

        var isRound2 by remember {
            mutableStateOf(false)
        }
        val transition = updateTransition(targetState = isRound2, label = "")
        val borderRadius2 by transition.animateInt(
            transitionSpec = { tween(2000) },
            label = "borderRadius",
            targetValueByState = { isRound ->
                if (isRound) 100 else 0
            }
        )
        val color by transition.animateColor(transitionSpec = { tween(1000) },
            label = "color", targetValueByState = { isRound ->
                if (isRound) Color.Red else GeneralTheme.colors.primary
            })

        Button(onClick = {
            isRound2 = !isRound2
        }) {
            Text(text = "Transition")
        }

        Box(
            modifier = Modifier
                .size(100.dp)
                .clip(RoundedCornerShape(borderRadius2))
                .background(color)
        )

    }
}