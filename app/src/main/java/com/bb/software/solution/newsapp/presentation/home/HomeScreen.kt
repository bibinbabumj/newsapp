package com.bb.software.solution.newsapp.presentation.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import com.bb.software.solution.newsapp.R
import com.bb.software.solution.newsapp.domain.model.Article
import com.bb.software.solution.newsapp.presentation.common.ArticleList
import com.bb.software.solution.newsapp.presentation.common.SearchBar
import com.bb.software.solution.newsapp.presentation.navgraph.Route
import com.bb.software.solution.newsapp.ui.theme.AppTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomScreen(articles: LazyPagingItems<Article>, navigateSearch: () -> Unit,navigateDetail: (Article) -> Unit) {
    val titles by remember {
        derivedStateOf {
            if (articles.itemCount > 10) {
                articles.itemSnapshotList.items
                    .slice(IntRange(start = 0, endInclusive = 9))
                    .joinToString(separator = " \uD83D\uDFE5 ") { it.title }
            } else {
                ""
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 24.dp)
            .statusBarsPadding()
    ) {

        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = null,
            modifier = Modifier
                .width(150.dp)
                .height(30.dp)
                .padding(horizontal = 24.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))
        SearchBar(
            modifier = Modifier.padding(horizontal = 24.dp),
            text = "",
            readOnly = true,
            onValueChange = {},
            onClick = {
                navigateSearch()
            },
            onSearch = {}
        )
        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = titles, modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp)
                .basicMarquee(), fontSize = 12.sp,
            color = colorResource(id = R.color.placeholder)
        )
        Spacer(modifier = Modifier.height(24.dp))

        ArticleList(
            modifier = Modifier.padding(horizontal = 24.dp),
            articles = articles,
            onClickArticle = {
                navigateDetail(it)
            }
        )
    }


}


@Composable
@Preview(showBackground = true)
fun HomScreenPreview() {
    AppTheme {

    }
}