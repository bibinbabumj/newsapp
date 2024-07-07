package com.bb.software.solution.newsapp.presentation.bookmark


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bb.software.solution.newsapp.domain.usecases.news.NewsNewsCases
import com.bb.software.solution.newsapp.domain.usecases.news.SelectArticle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor (private val getSavedArticlesUseCase: NewsNewsCases) : ViewModel() {

    private val _state = mutableStateOf(BookMarkState())
    val state: State<BookMarkState> = _state

    init {
        getBookMarkNews()
    }


    private fun getBookMarkNews() {
        getSavedArticlesUseCase.selectArticle().onEach {
            _state.value = _state.value.copy(articles = it)
        }.launchIn(viewModelScope)
    }
}