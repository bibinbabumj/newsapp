package com.bb.software.solution.newsapp.presentation.details

import com.bb.software.solution.newsapp.domain.model.Article

sealed class DetailsEvent {
    data class UpsertDeleteArticle(val article: Article):DetailsEvent()
    object RemoveSideEffect:DetailsEvent()
}