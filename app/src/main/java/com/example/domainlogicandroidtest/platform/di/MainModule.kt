package com.example.domainlogicandroidtest.platform.di

import com.example.domainlogicandroidtest.platform.App
import com.example.domainlogicandroidtest.platform.interactor.GetUsersInteractor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class MainModule(private val app: App) {

    @Binds
    @Singleton
    abstract fun getUsersInteractor(getUsersInteractor: GetUsersInteractor) : GetUsersInteractor

}