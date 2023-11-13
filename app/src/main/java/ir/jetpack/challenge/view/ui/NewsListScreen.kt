package ir.jetpack.challenge.view.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import ir.jetpack.challenge.R
import ir.jetpack.challenge.model.dto.Article
import ir.jetpack.challenge.viewmodel.MainViewModel

@Composable
fun NewsListScreen(
    navController: NavController,
    viewModel: MainViewModel = hiltViewModel()
) {
    val items: LazyPagingItems<Article> = viewModel.getList().collectAsLazyPagingItems()
    Column(
        modifier = Modifier
            .testTag("NewsList")
            .fillMaxSize()
            .background(Color.DarkGray)
    ) {
        Header(title = stringResource(R.string.title), true, navController = navController)
        NewsList(items)
    }
}