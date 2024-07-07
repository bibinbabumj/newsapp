package com.bb.software.solution.newsapp.domain.usecases.app_entry

import com.bb.software.solution.newsapp.domain.repository.manager.LocalUserManager

class SaveAppEntry(private val localUserManger: LocalUserManager) {
    suspend operator fun invoke(isAppEntry: Boolean) {
           localUserManger.saveAppEntry(isAppEntry)
    }
}