package com.bb.software.solution.newsapp.domain.usecases.news

import com.bb.software.solution.newsapp.data.local.NewsDao
import com.bb.software.solution.newsapp.domain.model.Article
import com.bb.software.solution.newsapp.domain.repository.remote.NewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class SelectArticle (private val newsRepository: NewsRepository) {
    operator fun invoke(): Flow<List<Article>> {
        return newsRepository.selectArticles().flowOn(Dispatchers.IO) //// Ensure the Flow emits on the IO dispatcher
    }
}