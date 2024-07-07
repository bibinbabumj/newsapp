package com.bb.software.solution.newsapp.domain.repository.manager

import kotlinx.coroutines.flow.Flow

interface LocalUserManager {
    val isAppEntry: Flow<Boolean>
    suspend fun saveAppEntry(isAppEntry: Boolean)
}