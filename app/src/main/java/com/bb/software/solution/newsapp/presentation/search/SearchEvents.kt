package com.bb.software.solution.newsapp.presentation.search

sealed class SearchEvents {
    data class UpdateSearchQuery(val query: String) : SearchEvents()
    object SearchNews : SearchEvents()
}