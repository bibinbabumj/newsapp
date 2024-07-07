package com.bb.software.solution.newsapp.presentation.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bb.software.solution.newsapp.domain.usecases.app_entry.AppEntryUsesCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(private val appEntryUsesCases: AppEntryUsesCases) :
    ViewModel() {

    fun onEvent(event: OnBoardingEvents, isAppEntry: Boolean) {
        when (event) {
            OnBoardingEvents.saveAppEntry -> onSaveAppEntry(isAppEntry)
        }

    }

    private fun onSaveAppEntry(isAppEntry: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            appEntryUsesCases.saveAppEntry(isAppEntry)
        }
    }

}