package com.bb.software.solution.newsapp.data.repository.manger

import android.content.Context
import com.bb.software.solution.newsapp.data.datasource.LocalUserManagerDataStore
import com.bb.software.solution.newsapp.domain.repository.manager.LocalUserManager
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalUserManagerImpl @Inject constructor(private val dataSource: LocalUserManagerDataStore) :
    LocalUserManager {
    override val isAppEntry: Flow<Boolean>
        get() = dataSource.isAppEntry

    override suspend fun saveAppEntry(isAppEntry: Boolean) {
        dataSource.saveAppEntry(isAppEntry)
    }

}