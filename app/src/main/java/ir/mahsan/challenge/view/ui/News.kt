package ir.mahsan.challenge.view.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import ir.mahsan.challenge.R
import ir.mahsan.challenge.model.dto.Article
import ir.mahsan.challenge.util.Utils
import ir.mahsan.challenge.util.timeAgo
import kotlinx.coroutines.flow.Flow

@Composable
fun NewsList(
    data: Flow<PagingData<Article>>,
    onItemClicked: ((String) -> Unit)? = null,
) {
    val listState: LazyListState = rememberLazyListState()
    val items: LazyPagingItems<Article> = data.collectAsLazyPagingItems()
    Column {
        Header(title = stringResource(R.string.title))
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .background(Color.DarkGray)
        ) {
            if (items.itemCount == 0 && items.loadState.refresh != LoadState.Loading) {
                var message: String? = null
                items.apply {
                    when {
                        loadState.refresh is LoadState.Error -> {
                            val e = loadState.refresh as LoadState.Error
                            message = Utils.getMessageError(e.error)
                        }
                        loadState.append is LoadState.Error -> {
                            val e = loadState.append as LoadState.Error
                            message = Utils.getMessageError(e.error)
                        }
                    }
                }
                Text(
                    text = message ?: stringResource(id = R.string.not_found_items),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
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
                        .padding(horizontal = 20.dp, vertical = 10.dp)
                        .align(Alignment.Center),
                    content = {
                        items(items.itemCount) { index ->
                            items[index]?.let {
                                NewsItem(
                                    it, onItemClicked = onItemClicked
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

@Composable
fun NewsItem(
    item: Article? = null, onItemClicked: ((String) -> Unit)? = null
) {
    Row(
        Modifier
            .testTag("NewsItem")
            .wrapContentSize()
            .border(2.dp, Color(0xFF222222))
            .padding(vertical = 5.dp)
            .clickable {
//                item?.let { onItemClicked?.invoke(it.) }
            }) {

        AsyncImage(
            model = item?.urlToImage,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(120.dp)
                .width(120.dp)
                .align(CenterVertically)
                .padding(10.dp)
                .clip(RoundedCornerShape(10.dp)))

        Column(
            modifier = Modifier
                .align(CenterVertically)
        ) {
            Text(
                text = item?.title ?: "",
                color = Color.White,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    lineHeight = 20.sp
                ),
                fontSize = 16.sp,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(10.dp),
                textAlign = TextAlign.Start,
                overflow = TextOverflow.Ellipsis
            )

            Row {
                Text(
                    text = item?.author ?: "unknown",
                    color = Color.LightGray,
                    fontSize = 12.sp,
                    style = TextStyle(
                        fontWeight = FontWeight.Normal
                    ),
                    modifier = Modifier
                        .align(CenterVertically)
                        .fillMaxWidth(.60f)
                        .padding(10.dp),
                    textAlign = TextAlign.Start,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                timeAgo(item?.publishedAt ?: "")?.let {
                    Text(
                        text = it,
                        color = Color.LightGray,
                        style = TextStyle(
                            fontWeight = FontWeight.Normal
                        ),
                        fontSize = 12.sp,
                        modifier = Modifier
                            .align(CenterVertically)
                            .padding(10.dp),
                        textAlign = TextAlign.Start,
                    )
                }
            }
        }
    }
}
