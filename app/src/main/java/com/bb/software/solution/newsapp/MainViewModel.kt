package com.bb.software.solution.newsapp

import androidx.lifecycle.ViewModel
import com.bb.software.solution.newsapp.domain.usecases.app_entry.AppEntryUsesCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.bb.software.solution.newsapp.presentation.navgraph.Route
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@HiltViewModel
class MainViewModel @Inject constructor(appEntryUsesCases: AppEntryUsesCases) :
    ViewModel() {
    var splashCondition by mutableStateOf(true)
        private set


    var startDestination by mutableStateOf(Route.AppStartNavigation.route)
        private set

    init {
        appEntryUsesCases.readAppEntry().onEach { isHomeScreen ->
            startDestination = if (isHomeScreen) {
                Route.NewsNavigation.route
            } else {
                Route.AppStartNavigation.route
            }

            delay(2000)
            splashCondition = false
        }.launchIn(viewModelScope)

    }
}