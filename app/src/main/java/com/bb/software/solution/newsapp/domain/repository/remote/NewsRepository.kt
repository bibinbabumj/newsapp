package com.bb.software.solution.newsapp.domain.repository.remote

import androidx.paging.PagingData
import com.bb.software.solution.newsapp.domain.model.Article
import com.bb.software.solution.newsapp.domain.model.Source
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun getNews(sources: List<String>):Flow<PagingData<Article>>
    fun searchNews(searchQuery: String,sources: List<String>):Flow<PagingData<Article>>
    suspend fun upsertArticle(article: Article)
    suspend fun deleteArticle(article: Article)
    fun selectArticles():Flow<List<Article>>
    suspend fun selectArticles(url: String): Article?



}