package ir.jetpack.challenge.view.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ir.jetpack.challenge.R
import ir.jetpack.challenge.common.Constants
import ir.jetpack.challenge.view.ui.theme.GeneralTheme

@Composable
fun Header(title: String, hasIcon: Boolean = false, navController: NavController? = null) {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
        Row(
            Modifier
                .background(GeneralTheme.colors.primary)
                .padding(horizontal = 12.dp, vertical = 16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = title,
                color = Color.White,
                style = TextStyle(
                    fontWeight = FontWeight.Bold
                ),
                fontSize = 20.sp
            )
            if (hasIcon) {
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    painter = painterResource(id = R.drawable.ic_animation),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.clickable {
                        navController?.navigate(Constants.ANIMATION_GRAPH_ROUTE)
                    })
            }
        }
    }
}
