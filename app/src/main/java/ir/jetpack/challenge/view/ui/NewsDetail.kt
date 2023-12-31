package ir.jetpack.challenge.view.ui

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
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ir.jetpack.challenge.R
import ir.jetpack.challenge.model.dto.Article
import ir.jetpack.challenge.util.getAuthorValue
import ir.jetpack.challenge.util.timeAgo
import ir.jetpack.challenge.view.ui.theme.LocalDim
import ir.jetpack.challenge.view.ui.theme.GeneralTheme

@Composable
fun NewsDetail(item: Article) {
    val uriHandler = LocalUriHandler.current

    Column(
        modifier = Modifier
            .background(GeneralTheme.colors.secondBackground)
            .fillMaxSize()
            .padding(LocalDim.current.spaceSLarge),
    ) {
        Text(
            text = item.title,
            color = GeneralTheme.colors.title,
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
                color = GeneralTheme.colors.text,
                fontSize = 12.sp,
                style = TextStyle(
                    fontWeight = FontWeight.Normal
                ),
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .fillMaxWidth(.60f)
                    .padding(LocalDim.current.spaceNormal),
                textAlign = TextAlign.Center,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = timeAgo(item.publishedAt),
                color = GeneralTheme.colors.text,
                style = TextStyle(
                    fontWeight = FontWeight.Normal
                ),
                fontSize = 12.sp,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(LocalDim.current.spaceNormal),
                textAlign = TextAlign.Center,
            )
        }

        Spacer(modifier = Modifier.height(LocalDim.current.spaceNormal))

        Text(
            text = stringResource(id = R.string.description),
            color = GeneralTheme.colors.title,
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                lineHeight = 20.sp
            ),
            fontSize = 14.sp,
            textAlign = TextAlign.Start,
        )

        Text(
            text = item.description,
            color = GeneralTheme.colors.text,
            style = TextStyle(
                fontWeight = FontWeight.Normal,
                lineHeight = 20.sp
            ),
            fontSize = 12.sp,
            modifier = Modifier
                .padding(bottom = 8.dp, top = LocalDim.current.spaceSmall),
            textAlign = TextAlign.Start,
        )

        Text(
            text = stringResource(id = R.string.content),
            color = GeneralTheme.colors.title,
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                lineHeight = 20.sp
            ),
            fontSize = 14.sp,
            textAlign = TextAlign.Start,
        )

        Text(
            text = item.content,
            color = GeneralTheme.colors.text,
            style = TextStyle(
                fontWeight = FontWeight.Normal,
                lineHeight = 20.sp
            ),
            modifier = Modifier
                .padding(top = LocalDim.current.spaceSmall),
            fontSize = 12.sp,
            textAlign = TextAlign.Start,
        )

        Button(
            onClick = {
                uriHandler.openUri(item.url)
            },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = GeneralTheme.colors.primary,
                contentColor = GeneralTheme.colors.title,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = LocalDim.current.spaceSLarge)
        ) {
            Text(text = stringResource(id = R.string.read_more))
        }
    }
}