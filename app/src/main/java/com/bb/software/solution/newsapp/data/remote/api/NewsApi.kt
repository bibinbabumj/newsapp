package com.bb.software.solution.newsapp.data.remote.api

import com.bb.software.solution.newsapp.data.remote.dto.NewsResponse
import com.bb.software.solution.newsapp.util.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("everything")
    suspend fun getNews(
        @Query("page") page: Int,
        @Query("sources") sources: String,
        @Query("apiKey") apiKey: String= Constants.API_ENTRY
    ): NewsResponse


    @GET("everything")
    suspend fun searchNews(
        @Query("q") searchQuery: String,
        @Query("page") page: Int,
        @Query("sources") sources: String,
        @Query("apiKey") apiKey: String= Constants.API_ENTRY
    ): NewsResponse
}