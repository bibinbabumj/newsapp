package com.bb.software.solution.newsapp.data.remote.dto

import com.bb.software.solution.newsapp.domain.model.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)