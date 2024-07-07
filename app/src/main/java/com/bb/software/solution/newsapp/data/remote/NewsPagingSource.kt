package com.bb.software.solution.newsapp.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bb.software.solution.newsapp.data.remote.api.NewsApi
import com.bb.software.solution.newsapp.domain.model.Article
import com.bb.software.solution.newsapp.util.Constants.API_ENTRY

class NewsPagingSource(
    private val api: NewsApi,
    private val sources: String
) : PagingSource<Int, Article>() {
    private var totalNewsCount = 0

    /**
     * Returns the refresh key for the current state of the PagingSource.
     *
     * The refresh key helps determine how to reload data after an update or a refresh.
     *
     * @param state The current [PagingState] containing information about the paging state.
     * @return The refresh key to use for loading data after an update, or null if unavailable.
     */


    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { anchorPosition->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }


    /**
     * Loads a page of news articles from the API based on the given [params].
     *
     * @param params Parameters for loading data, including the page key.
     * @return A [LoadResult] containing the loaded data ([Article]s) and pagination keys.
     */

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val page = params.key ?: 1
        return try {
            val newsResponse = api.getNews(sources = sources, page = page)
            totalNewsCount += newsResponse.articles.size
            val articles = newsResponse.articles.distinctBy {
                it.title
            }
            LoadResult.Page(
                data = articles,
                nextKey = if (totalNewsCount >= newsResponse.totalResults) null else page + 1,
                prevKey = null
            )

        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(e)
        }
    }


}