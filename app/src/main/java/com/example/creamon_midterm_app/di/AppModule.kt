package com.example.creamon_midterm_app.di

import com.example.creamon_midterm_app.data.common.HandleResponse
import com.example.creamon_midterm_app.data.service.authentication.AuthenticationService
import com.example.creamon_midterm_app.data.service.authentication.AuthenticationServiceImpl
import com.example.creamon_midterm_app.data.service.store_items.StoreItemsService
import com.google.firebase.auth.FirebaseAuth
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val BASE_URL = "https://run.mocky.io/v3/"

    @Singleton
    @Provides
    fun provideHandleResponse(): HandleResponse {
        return HandleResponse()
    }

    @Singleton
    @Provides
    fun provideFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Singleton
    @Provides
    fun provideAuthenticationService(firebaseAuth: FirebaseAuth): AuthenticationService {
        return AuthenticationServiceImpl(firebaseAuth)
    }

    @Singleton
    @Provides
    fun provideRetrofitClient(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
                )
            )
            .build()
    }

    @Singleton
    @Provides
    fun provideConnectionsService(retrofit: Retrofit): StoreItemsService {
        return retrofit.create(StoreItemsService::class.java)
    }
}