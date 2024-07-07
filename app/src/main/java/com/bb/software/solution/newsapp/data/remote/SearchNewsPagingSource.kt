package com.bb.software.solution.newsapp.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bb.software.solution.newsapp.data.remote.api.NewsApi
import com.bb.software.solution.newsapp.domain.model.Article

class SearchNewsPagingSource(
    private val api: NewsApi,
    private val sources: String,
    private val searchQuery: String
) :
    PagingSource<Int, Article>() {
    private var totalNewsCount = 0
    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }

    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {

        try {
            val pageNumber = params.key ?: 1
            val newsResponse =
                api.searchNews(searchQuery = searchQuery, sources = sources, page = pageNumber)
            // Process fetched articles
            val articles = newsResponse.articles.distinctBy { it.title }
            // Update total news count
            totalNewsCount += articles.size
            // Determine next key for pagination
            val nextKey = if (totalNewsCount >= newsResponse.totalResults) null else pageNumber + 1
            return LoadResult.Page(
                data = articles,
                prevKey = null,
                nextKey = nextKey
            )


        } catch (e: Exception) {
            e.printStackTrace()
            return LoadResult.Error(e)
        }
    }

}