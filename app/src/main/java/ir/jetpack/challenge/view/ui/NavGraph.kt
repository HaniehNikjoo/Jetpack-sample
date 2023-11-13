package ir.jetpack.challenge.view.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ir.jetpack.challenge.common.Constants.ANIMATION_GRAPH_ROUTE
import ir.jetpack.challenge.common.Constants.HOME_GRAPH_ROUTE

@Composable
fun SetupNavGraph(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = HOME_GRAPH_ROUTE,
    ) {
        composable(HOME_GRAPH_ROUTE) { NewsListScreen(navController) }
        composable(ANIMATION_GRAPH_ROUTE) { Animations(navController) }
    }
}