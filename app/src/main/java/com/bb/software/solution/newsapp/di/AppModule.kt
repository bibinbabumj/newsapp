package com.bb.software.solution.newsapp.di

import android.app.Application
import androidx.room.Room
import com.bb.software.solution.newsapp.data.datasource.LocalUserManagerDataStore
import com.bb.software.solution.newsapp.data.local.NewsDao
import com.bb.software.solution.newsapp.data.local.NewsDateBase
import com.bb.software.solution.newsapp.data.local.NewsTypeConverter
import com.bb.software.solution.newsapp.data.remote.api.NewsApi
import com.bb.software.solution.newsapp.data.repository.manger.LocalUserManagerImpl
import com.bb.software.solution.newsapp.data.repository.newsrepo.NewsRepositoryImpl
import com.bb.software.solution.newsapp.domain.repository.manager.LocalUserManager
import com.bb.software.solution.newsapp.domain.repository.remote.NewsRepository
import com.bb.software.solution.newsapp.domain.usecases.app_entry.AppEntryUsesCases
import com.bb.software.solution.newsapp.domain.usecases.app_entry.ReadAppEntry
import com.bb.software.solution.newsapp.domain.usecases.app_entry.SaveAppEntry
import com.bb.software.solution.newsapp.domain.usecases.news.DeleteArticle
import com.bb.software.solution.newsapp.domain.usecases.news.GetNews
import com.bb.software.solution.newsapp.domain.usecases.news.NewsNewsCases
import com.bb.software.solution.newsapp.domain.usecases.news.SearchNews
import com.bb.software.solution.newsapp.domain.usecases.news.SelectArticle
import com.bb.software.solution.newsapp.domain.usecases.news.SelectSingleArticle
import com.bb.software.solution.newsapp.domain.usecases.news.UpsertArticle
import com.bb.software.solution.newsapp.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideLocalUserManagerDataStore(application: Application): LocalUserManagerDataStore {
        return LocalUserManagerDataStore(application)
    }

    @Provides
    @Singleton
    fun provideLocalUserManager(localUserManagerDataStore: LocalUserManagerDataStore): LocalUserManager {
        return LocalUserManagerImpl(localUserManagerDataStore)
    }

    @Provides
    @Singleton
    fun provideAppEntryUseCases(localUserManager: LocalUserManager) = AppEntryUsesCases(
        readAppEntry = ReadAppEntry(localUserManager),
        saveAppEntry = SaveAppEntry(localUserManager)
    )


    @Provides
    @Singleton
    fun provideNewsApi(): NewsApi {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(NewsApi::class.java)
    }


    @Provides
    @Singleton
    fun provideNewsRepository(newsApi: NewsApi,newsDao: NewsDao): NewsRepository = NewsRepositoryImpl(newsApi, newsDao = newsDao)

    @Provides
    @Singleton
    fun provideNewsUseCases(newsRepository: NewsRepository): NewsNewsCases {
        return NewsNewsCases(
            getNews = GetNews(newsRepository),
            searchNews = SearchNews(newsRepository),
            upsertArticle = UpsertArticle(newsRepository),
            deleteArticle = DeleteArticle(newsRepository),
            selectArticle = SelectArticle(newsRepository),
            selectSingleArticle = SelectSingleArticle(newsRepository)

        )
    }


    @Provides
    @Singleton
    fun provideNewsDatabase(
        application: Application
    ): NewsDateBase {
        return Room.databaseBuilder(
            context = application,
            klass = NewsDateBase::class.java,
            name = "news_db"
        ).addTypeConverter(NewsTypeConverter())
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsDao(newsDateBase: NewsDateBase) = newsDateBase.newsDao


//    @Provides
//    @Singleton
//    fun provideApiInstance(): NewsApi {
//        return Retrofit
//            .Builder()
//            .baseUrl(BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//            .create(NewsApi::class.java)
//    }
//
//    @Provides
//    @Singleton
//    fun provideNewsDatabase(
//        application: Application
//    ): NewsDateBase {
//        return Room.databaseBuilder(
//            context = application,
//            klass = NewsDateBase::class.java,
//            name = "news_db"
//        ).addTypeConverter(NewsTypeConverter())
//            .fallbackToDestructiveMigration()
//            .build()
//    }
//
//    @Provides
//    @Singleton
//    fun provideNewsDao(
//        newsDatabase: NewsDateBase
//    ): NewsDao = newsDatabase.newsDao


}