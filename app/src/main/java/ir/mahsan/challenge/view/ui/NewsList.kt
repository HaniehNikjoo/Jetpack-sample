package ir.mahsan.challenge.view.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.CircularProgressIndicator
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
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import ir.mahsan.challenge.R
import ir.mahsan.challenge.model.dto.Article
import ir.mahsan.challenge.util.getMessageError
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow

@Composable
fun NewsList(
    data: Flow<PagingData<Article>>,
    onItemClicked: ((Article) -> Unit)? = null,
) {
    val listState: LazyListState = rememberLazyListState()
    val items: LazyPagingItems<Article> = data.collectAsLazyPagingItems()
    var isLoading by remember {
        mutableStateOf(true)
    }
    LaunchedEffect(key1 = true) {
        delay(5000)
        isLoading = false
    }
    Column {
        Header(title = stringResource(R.string.title))
        Box(
            modifier = Modifier
                .testTag("NewsList")
                .fillMaxHeight()
                .background(Color.DarkGray)
        ) {
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
                    .background(Color(0xFF222222))
            ) {
                LazyColumn(state = listState,
                    modifier = Modifier
                        .wrapContentHeight()
                        .fillMaxWidth()
                        .background(color = Color(0xFF222222))
                        .padding(horizontal = 20.dp)
                        .align(Alignment.Center),
                    content = {
                        items(items.itemCount) { index ->
                            items[index]?.let {
                                ShimmerListItem(
                                    isLoading = isLoading,
                                    contentAfterLoading = {
                                        NewsItem(
                                            it, onItemClicked = onItemClicked
                                        )
                                    }
                                )
                            }
                        }
                    })
            }
            if (items.itemCount == 0 && items.loadState.refresh == LoadState.Loading) CircularProgressIndicator(
                modifier = Modifier
                    .size(60.dp)
                    .padding(10.dp)
                    .align(Alignment.Center)
                    .wrapContentSize(), color = Color.White
            )
        }
    }
}