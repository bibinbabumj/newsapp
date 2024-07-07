//package com.bb.software.solution.newsapp.di
//
//import com.bb.software.solution.newsapp.data.repository.newsrepo.NewsRepositoryImpl
//import com.bb.software.solution.newsapp.domain.repository.remote.NewsRepository
//import dagger.Binds
//import dagger.Module
//import dagger.hilt.InstallIn
//import dagger.hilt.components.SingletonComponent
//import javax.inject.Singleton
//
//@Module
//@InstallIn(SingletonComponent::class)
//abstract class RepositoryModule {
//
//    @Binds
//    @Singleton
//    abstract fun bindNewsRepository(newsRepositoryImpl: NewsRepositoryImpl): NewsRepository
//
//}