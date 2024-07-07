package com.bb.software.solution.newsapp.presentation.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import com.bb.software.solution.newsapp.domain.model.Article
import com.bb.software.solution.newsapp.presentation.common.ArticleList
import com.bb.software.solution.newsapp.presentation.common.SearchBar

@Composable
fun SearchScreen(
    state: SearchState,
    events: (SearchEvents) -> Unit,
    navigateToDetails: (Article) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(top = 24.dp, start = 24.dp, end = 24.dp)
            .statusBarsPadding()
            .fillMaxSize()
    ) {

        SearchBar(
            text = state.searchQuery,
            readOnly = false,
            onValueChange = { events(SearchEvents.UpdateSearchQuery(it)) },
            onSearch = { events(SearchEvents.SearchNews) }
        )
        Spacer(modifier = Modifier.height(24.dp))

        state.article?.let {
            val articles = it.collectAsLazyPagingItems()
            ArticleList(
                articles = articles,
                onClickArticle = { navigateToDetails(it) }
            )
        }
    }

}