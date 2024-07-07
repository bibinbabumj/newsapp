package com.bb.software.solution.newsapp.presentation.search

import androidx.paging.PagingData
import com.bb.software.solution.newsapp.domain.model.Article
import kotlinx.coroutines.flow.Flow

data class SearchState(
    val searchQuery: String = "",
    val article: Flow<PagingData<Article>>? = null

)