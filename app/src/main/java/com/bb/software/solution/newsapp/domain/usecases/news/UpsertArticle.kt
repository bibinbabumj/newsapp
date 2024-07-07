package com.bb.software.solution.newsapp.domain.usecases.news

import com.bb.software.solution.newsapp.data.local.NewsDao
import com.bb.software.solution.newsapp.domain.model.Article
import com.bb.software.solution.newsapp.domain.repository.remote.NewsRepository

class UpsertArticle(private val newsRepository: NewsRepository) {
    suspend operator fun invoke(article: Article) {
        newsRepository.deleteArticle(article)
    }
}