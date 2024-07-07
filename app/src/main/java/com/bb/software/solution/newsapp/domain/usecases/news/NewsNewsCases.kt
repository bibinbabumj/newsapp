package com.bb.software.solution.newsapp.domain.usecases.news

data class NewsNewsCases(
    val getNews: GetNews,
    val searchNews:SearchNews,
    val upsertArticle:UpsertArticle,
    val deleteArticle:DeleteArticle,
    val selectArticle: SelectArticle,
    val selectSingleArticle: SelectSingleArticle
)