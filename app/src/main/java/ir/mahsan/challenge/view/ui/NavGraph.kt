package ir.mahsan.challenge.view.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ir.mahsan.challenge.common.Constants.HOME_GRAPH_ROUTE

@Composable
fun SetupNavGraph(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = HOME_GRAPH_ROUTE,
    ) {
        composable(HOME_GRAPH_ROUTE) { NewsListScreen() }
    }
}