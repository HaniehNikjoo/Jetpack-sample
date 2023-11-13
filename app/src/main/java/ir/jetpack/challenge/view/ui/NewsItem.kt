package ir.jetpack.challenge.view.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import ir.jetpack.challenge.R
import ir.jetpack.challenge.model.dto.Article
import ir.jetpack.challenge.util.getAuthorValue
import ir.jetpack.challenge.util.timeAgo
import ir.jetpack.challenge.view.ui.theme.LocalDim
import ir.jetpack.challenge.view.ui.theme.GeneralTheme
import ir.jetpack.challenge.view.ui.theme.Shapes

@Composable
fun NewsItem(
    item: Article, onItemClicked: (Article) -> Unit
) {
    Row(
        Modifier
            .testTag("NewsItem")
            .wrapContentSize()
            .border(2.dp, GeneralTheme.colors.background)
            .padding(vertical = LocalDim.current.spaceSmall)
            .clickable {
                onItemClicked(item)
            }) {

        AsyncImage(
            model = item.urlToImage,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            error = painterResource(R.drawable.ic_place_holder),
            placeholder = painterResource(R.drawable.ic_place_holder),
            modifier = Modifier
                .height(LocalDim.current.spaceXLarge)
                .width(LocalDim.current.spaceXLarge)
                .align(CenterVertically)
                .padding(LocalDim.current.spaceNormal)
                .clip(Shapes.medium)
        )

        Column(
            modifier = Modifier
                .align(CenterVertically)
        ) {
            Text(
                text = item.title,
                color = GeneralTheme.colors.title,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    lineHeight = 20.sp
                ),
                fontSize = 16.sp,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(LocalDim.current.spaceNormal),
                maxLines = 3,
                textAlign = TextAlign.Start,
                overflow = TextOverflow.Ellipsis
            )

            Row {
                Text(
                    text = getAuthorValue(item.author),
                    color = GeneralTheme.colors.text,
                    fontSize = 12.sp,
                    style = TextStyle(
                        fontWeight = FontWeight.Normal
                    ),
                    modifier = Modifier
                        .align(CenterVertically)
                        .fillMaxWidth(.60f)
                        .padding(LocalDim.current.spaceNormal),
                    textAlign = TextAlign.Start,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                if(timeAgo(item.publishedAt).isNotEmpty()) {
                    Text(
                        text = timeAgo(item.publishedAt),
                        color = GeneralTheme.colors.text,
                        style = TextStyle(
                            fontWeight = FontWeight.Normal
                        ),
                        fontSize = 12.sp,
                        modifier = Modifier
                            .align(CenterVertically)
                            .padding(LocalDim.current.spaceNormal),
                        textAlign = TextAlign.Start,
                    )
                }
            }
        }
    }
}
