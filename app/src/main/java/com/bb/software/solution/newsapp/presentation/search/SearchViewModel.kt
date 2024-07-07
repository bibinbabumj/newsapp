package com.bb.software.solution.newsapp.presentation.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.bb.software.solution.newsapp.domain.usecases.news.NewsNewsCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val newsCases: NewsNewsCases) : ViewModel() {

    private val _state = mutableStateOf(SearchState())
    val state: State<SearchState> = _state

    fun onEvent(event: SearchEvents) {
        when (event) {
            is SearchEvents.SearchNews -> {
                mSearchNews()
            }

            is SearchEvents.UpdateSearchQuery -> {
                _state.value = _state.value.copy(searchQuery = event.query)
            }
        }
    }

    private fun mSearchNews() {
        val article = newsCases.searchNews(
            searchQuery = state.value.searchQuery,
            sources = listOf("bbc-news","abc-news","al-jazeera-english")
        )

        _state.value=_state.value.copy(article=article)
    }
}