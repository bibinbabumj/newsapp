package com.bb.software.solution.newsapp.domain.usecases.news

import androidx.paging.PagingData
import com.bb.software.solution.newsapp.domain.model.Article
import com.bb.software.solution.newsapp.domain.repository.remote.NewsRepository
import kotlinx.coroutines.flow.Flow

class SearchNews (private val newsRepository: NewsRepository) {
    operator fun invoke(sources: List<String>,searchQuery: String): Flow<PagingData<Article>> {
        return newsRepository.searchNews(searchQuery=searchQuery,sources)
    }
}