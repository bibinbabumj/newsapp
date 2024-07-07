package com.bb.software.solution.newsapp.presentation.common

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.bb.software.solution.newsapp.R
import com.bb.software.solution.newsapp.domain.model.Article
import com.bb.software.solution.newsapp.domain.model.Source
import com.bb.software.solution.newsapp.presentation.Dimens.articleCardSize
import com.bb.software.solution.newsapp.presentation.Dimens.padding3
import com.bb.software.solution.newsapp.ui.theme.AppTheme

@Composable
fun ArticleCard(
    modifier: Modifier = Modifier, article: Article, onArticleClicked: () -> Unit
) {
    val context = LocalContext.current

    Row(modifier.clickable { onArticleClicked() }) {
        AsyncImage(
            modifier = Modifier
                .size(articleCardSize)
                .clip(MaterialTheme.shapes.medium),
            contentScale = ContentScale.Crop,
            model = ImageRequest.Builder(context).data(article.urlToImage).build(),
            contentDescription = null
        )
        Column(
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .padding(horizontal = padding3)
                .height(articleCardSize)
        ) {

            Text(
                text = article.title,
                style = MaterialTheme.typography.bodyMedium,
                color = colorResource(id = R.color.text_medium),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = article.source.name,
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                    color = colorResource(id = R.color.body)
                )

                Spacer(modifier = Modifier.width(6.dp))
               Icon(
                   painter = painterResource(id = R.drawable.ic_time) ,
                   contentDescription = null,
                   tint = colorResource(id = R.color.body)
               )
                Spacer(modifier = Modifier.width(6.dp))

                Text(
                    text = article.publishedAt,
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                    color = colorResource(id = R.color.body)
                )
            }

        }
    }
}


@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun ArticleCardPreview() {
    AppTheme {
        ArticleCard(
            article = Article(
                title = "Hello cbsbbcsbbasc kjcasibacsi ascihiasc acisihsac kiisahc kicasihasci ",
                author = "bibin",
                content = "dhsvudsiusd",
                description = "cgsauysgus",
                publishedAt = "2 hours",
                url = "",
                urlToImage = "",
                source = Source(id = "", name = "BBC")
            )
        ) {

        }
    }

}
