package com.bb.software.solution.newsapp.presentation.bookmark

import com.bb.software.solution.newsapp.domain.model.Article

data class BookMarkState(
    val articles: List<Article> = emptyList(),


    )
