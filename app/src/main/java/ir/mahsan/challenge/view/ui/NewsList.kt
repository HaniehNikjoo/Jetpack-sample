package ir.mahsan.challenge.view.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import ir.mahsan.challenge.R
import ir.mahsan.challenge.model.dto.Article
import ir.mahsan.challenge.util.getMessageError
import ir.mahsan.challenge.view.ui.theme.LocalDim
import ir.mahsan.challenge.view.ui.theme.MahsanTheme
import ir.mahsan.challenge.viewmodel.MainViewModel
import kotlinx.coroutines.delay

@Composable
fun ListingScreen(
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

@Composable
fun NewsList(
    items: LazyPagingItems<Article>,
) {
    val listState: LazyListState = rememberLazyListState()
    var isLoading by remember { mutableStateOf(true) }
    LaunchedEffect(key1 = true) {
        // a fixed delay just to show the skeleton
        delay(3000)
        isLoading = items.itemCount == 0 && items.loadState.refresh == LoadState.Loading
    }

    lateinit var article: Article
    var isBottomSheetVisible by remember { mutableStateOf(false) }
    if (isBottomSheetVisible) {
        BottomSheet(article) {
            isBottomSheetVisible = false
        }
    }

    Box {
        if (items.itemCount == 0 && items.loadState.refresh != LoadState.Loading) {
            var message: String? = null
            items.apply {
                when {
                    loadState.refresh is LoadState.Error -> {
                        val e = loadState.refresh as LoadState.Error
                        message = getMessageError(e.error)
                    }

                    loadState.append is LoadState.Error -> {
                        val e = loadState.append as LoadState.Error
                        message = getMessageError(e.error)
                    }
                }
            }
            Text(
                text = message ?: stringResource(id = R.string.not_found_items),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .testTag("NewsListEmptyState")
                    .padding(16.dp)
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .align(Alignment.Center),
                color = Color.LightGray
            )
        } else Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MahsanTheme.colors.background)
        ) {
            LazyColumn(state = listState,
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .background(MahsanTheme.colors.background)
                    .padding(horizontal = LocalDim.current.spaceMedium)
                    .align(Alignment.Center),
                content = {
                    items(items.itemCount) { index ->
                        items[index]?.let {
                            ShimmerListItem(isLoading = isLoading, contentAfterLoading = {
                                NewsItem(it, onItemClicked = {
                                    article = it
                                    isBottomSheetVisible = true
                                })
                            })
                        }
                    }
                })
        }
    }

}