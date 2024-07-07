//package com.bb.software.solution.newsapp.di
//
//import com.bb.software.solution.newsapp.data.datasource.LocalUserManagerDataStore
//import com.bb.software.solution.newsapp.domain.repository.manager.LocalUserManager
//import dagger.Binds
//import dagger.Module
//import dagger.hilt.InstallIn
//import dagger.hilt.components.SingletonComponent
//import javax.inject.Singleton
//
//@Module
//@InstallIn(SingletonComponent::class)
//abstract class MangerModule {
//    @Binds
//    @Singleton
//    abstract fun bindLocalUserManger(localUserMangerImpl: LocalUserManagerDataStore) : LocalUserManager
//}