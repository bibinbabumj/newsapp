package com.bb.software.solution.newsapp.presentation.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bb.software.solution.newsapp.domain.model.Article
import com.bb.software.solution.newsapp.domain.usecases.news.DeleteArticle
import com.bb.software.solution.newsapp.domain.usecases.news.NewsNewsCases
import com.bb.software.solution.newsapp.domain.usecases.news.SelectSingleArticle
import com.bb.software.solution.newsapp.domain.usecases.news.UpsertArticle
import com.bb.software.solution.newsapp.util.UIComponent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val newsCases: NewsNewsCases
) : ViewModel() {
    var sideEffect by mutableStateOf<UIComponent?>(null)
        private set

    fun onEvent(event: DetailsEvent) {
        when (event) {
            DetailsEvent.RemoveSideEffect -> {
                sideEffect = null

            }

            is DetailsEvent.UpsertDeleteArticle -> {
                viewModelScope.launch {
                    val article = newsCases.selectSingleArticle(event.article.url)
                    if (article == null) {
                        mUpsertArticle(event.article)

                    } else {
                        mDeleteArticle(event.article)
                    }
                }

            }
        }
    }


    private suspend fun mUpsertArticle(article: Article) {
        newsCases.upsertArticle(article = article)
        sideEffect = UIComponent.Toast("Article deleted")
    }

    private suspend fun mDeleteArticle(article: Article) {
        newsCases.deleteArticle(article = article)
        sideEffect = UIComponent.Toast("Article Inserted")
    }


}