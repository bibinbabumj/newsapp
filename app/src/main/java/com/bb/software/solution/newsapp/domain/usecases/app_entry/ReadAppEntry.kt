package com.bb.software.solution.newsapp.domain.usecases.app_entry

import com.bb.software.solution.newsapp.domain.repository.manager.LocalUserManager
import kotlinx.coroutines.flow.Flow


class ReadAppEntry(private val localUserManager: LocalUserManager){
   operator fun invoke(): Flow<Boolean> =localUserManager.isAppEntry
}