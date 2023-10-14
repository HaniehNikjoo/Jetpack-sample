package ir.mahsan.challenge.view.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ir.mahsan.challenge.model.dto.Article
import ir.mahsan.challenge.util.getAuthorValue
import ir.mahsan.challenge.util.timeAgo
import ir.mahsan.challenge.view.ui.theme.MahsanTheme

@Composable
fun NewsDetail(item: Article) {
    val uriHandler = LocalUriHandler.current

    Column(
        modifier = Modifier
            .background(Color(0xFF303031))
            .fillMaxSize()
            .padding(20.dp),
    ) {
        Text(
            text = item.title,
            color = Color.White,
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                lineHeight = 28.sp
            ),
            fontSize = 22.sp,
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            overflow = TextOverflow.Ellipsis
        )

        Row {
            Text(
                text = getAuthorValue(item.author),
                color = Color.LightGray,
                fontSize = 12.sp,
                style = TextStyle(
                    fontWeight = FontWeight.Normal
                ),
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .fillMaxWidth(.60f)
                    .padding(10.dp),
                textAlign = TextAlign.Center,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = timeAgo(item.publishedAt) ?: "",
                color = Color.LightGray,
                style = TextStyle(
                    fontWeight = FontWeight.Normal
                ),
                fontSize = 12.sp,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(10.dp),
                textAlign = TextAlign.Center,
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Description",
            color = Color.White,
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                lineHeight = 20.sp
            ),
            fontSize = 14.sp,
            textAlign = TextAlign.Start,
        )

        Text(
            text = item.description,
            color = Color.LightGray,
            style = TextStyle(
                fontWeight = FontWeight.Normal,
                lineHeight = 20.sp
            ),
            fontSize = 14.sp,
            modifier = Modifier
                .padding(bottom = 8.dp),
            textAlign = TextAlign.Start,
        )

        Text(
            text = "Content",
            color = Color.White,
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                lineHeight = 20.sp
            ),
            fontSize = 14.sp,
            textAlign = TextAlign.Start,
        )

        Text(
            text = item.content,
            color = Color.LightGray,
            style = TextStyle(
                fontWeight = FontWeight.Normal,
                lineHeight = 20.sp
            ),
            fontSize = 14.sp,
            textAlign = TextAlign.Start,
        )

        Button(
            onClick = {
                uriHandler.openUri(item.url)
            },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MahsanTheme.colors.primary,
                contentColor = Color.White,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp)
        ) {
            Text(text = "Read More")
        }
    }
}