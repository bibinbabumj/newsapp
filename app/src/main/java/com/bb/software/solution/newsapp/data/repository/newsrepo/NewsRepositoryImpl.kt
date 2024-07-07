package com.bb.software.solution.newsapp.data.repository.newsrepo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.bb.software.solution.newsapp.data.local.NewsDao
import com.bb.software.solution.newsapp.data.remote.NewsPagingSource
import com.bb.software.solution.newsapp.data.remote.SearchNewsPagingSource
import com.bb.software.solution.newsapp.data.remote.api.NewsApi
import com.bb.software.solution.newsapp.domain.model.Article
import com.bb.software.solution.newsapp.domain.repository.remote.NewsRepository
import kotlinx.coroutines.flow.Flow

class NewsRepositoryImpl(private val newsApi: NewsApi,
    private val newsDao: NewsDao) : NewsRepository {
    override fun getNews(sources: List<String>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                NewsPagingSource(newsApi, sources.joinToString(","))
            }

        ).flow

    }

    override fun searchNews(searchQuery: String, sources: List<String>): Flow<PagingData<Article>> {
        val pager = Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                SearchNewsPagingSource(
                    newsApi,
                    sources = sources.joinToString(","),
                    searchQuery = searchQuery
                )
            }
        )

        return pager.flow
    }

    override suspend fun upsertArticle(article: Article) {
        newsDao.upsert(article)
    }

    override suspend fun deleteArticle(article: Article) {
        newsDao.delete(article)
    }

    override fun selectArticles(): Flow<List<Article>> {
       return newsDao.getArticles()
    }

    override suspend fun selectArticles(url: String): Article? {
        return  newsDao.getArticle(url)
    }

}