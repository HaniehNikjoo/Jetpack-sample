package ir.mahsan.challenge.view.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
import ir.mahsan.challenge.R
import ir.mahsan.challenge.model.dto.Article
import ir.mahsan.challenge.util.getAuthorValue
import ir.mahsan.challenge.util.timeAgo

@Composable
fun NewsItem(
    item: Article? = null, onItemClicked: ((Article) -> Unit)? = null
) {
    Row(
        Modifier
            .testTag("NewsItem")
            .wrapContentSize()
            .border(2.dp, Color(0xFF222222))
            .padding(vertical = 5.dp)
            .clickable {
                item?.let { onItemClicked?.invoke(item) }
            }) {

        AsyncImage(
            model = item?.urlToImage,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            error = painterResource(R.drawable.ic_place_holder),
            placeholder = painterResource(R.drawable.ic_place_holder),
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
                    text = getAuthorValue(item?.author),
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
