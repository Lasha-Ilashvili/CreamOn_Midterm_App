package com.example.creamon_midterm_app.di

import com.example.creamon_midterm_app.data.common.HandleResponse
import com.example.creamon_midterm_app.data.service.authentication.AuthenticationService
import com.example.creamon_midterm_app.data.service.authentication.AuthenticationServiceImpl
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Singleton
    @Provides
    fun provideHandleResponse(): HandleResponse {
        return HandleResponse()
    }

    @Singleton
    @Provides
    fun provideAuthenticationService(firebaseAuth: FirebaseAuth): AuthenticationService {
        return AuthenticationServiceImpl(firebaseAuth)
    }
}