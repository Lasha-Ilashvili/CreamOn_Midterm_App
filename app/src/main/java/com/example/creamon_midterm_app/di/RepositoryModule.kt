package com.example.creamon_midterm_app.di

import com.example.creamon_midterm_app.data.common.HandleResponse
import com.example.creamon_midterm_app.data.repository.authentication.AuthenticationRepositoryImpl
import com.example.creamon_midterm_app.data.repository.store_items.StoreItemsRepositoryImpl
import com.example.creamon_midterm_app.data.service.authentication.AuthenticationService
import com.example.creamon_midterm_app.data.service.store_items.StoreItemsService
import com.example.creamon_midterm_app.domain.repository.authentication.AuthenticationRepository
import com.example.creamon_midterm_app.domain.repository.store_items.StoreItemsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideAuthenticationRepository(
        authenticationService: AuthenticationService,
        handleResponse: HandleResponse
    ): AuthenticationRepository {
        return AuthenticationRepositoryImpl(
            authenticationService = authenticationService,
            handleResponse = handleResponse
        )
    }

    @Singleton
    @Provides
    fun provideStoreItemsRepository(
        storeItemsService: StoreItemsService,
        handleResponse: HandleResponse
    ): StoreItemsRepository {
        return StoreItemsRepositoryImpl(
            storeItemsService = storeItemsService,
            handleResponse = handleResponse
        )
    }
}