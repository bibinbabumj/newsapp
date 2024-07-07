package com.bb.software.solution.newsapp.data.datasource

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.bb.software.solution.newsapp.util.Constants.APP_ENTRY
import com.bb.software.solution.newsapp.util.Constants.LOCAL_USER_MANAGER
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map



class LocalUserManagerDataStore(private val context: Context) {
    private val Context.dataStore by preferencesDataStore(name = LOCAL_USER_MANAGER)

    private companion object {
        val IS_APP_ENTRY = booleanPreferencesKey(APP_ENTRY)
        // val NAME = stringPreferencesKey("name")
        //  val ADDRESS = stringPreferencesKey("address")
    }

    val isAppEntry: Flow<Boolean> =
        context.dataStore.data.map { preferences -> preferences[IS_APP_ENTRY] ?: false }


    suspend fun saveAppEntry(isAppEntry: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[IS_APP_ENTRY] = isAppEntry
        }
    }
}

