package ir.mahsan.challenge.view.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import ir.mahsan.challenge.R
import ir.mahsan.challenge.model.dto.Article
import ir.mahsan.challenge.viewmodel.MainViewModel

@Composable
fun NewsListScreen(
    viewModel: MainViewModel = hiltViewModel()
) {
    val items: LazyPagingItems<Article> = viewModel.getList().collectAsLazyPagingItems()
    Column(
        modifier = Modifier
            .testTag("NewsList")
            .fillMaxSize()
            .background(Color.DarkGray)
    ) {
        Header(title = stringResource(R.string.title))
        NewsList(items)
    }
}