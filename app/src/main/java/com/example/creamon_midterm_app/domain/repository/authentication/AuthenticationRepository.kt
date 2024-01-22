package com.example.creamon_midterm_app.domain.repository.authentication

import com.example.creamon_midterm_app.data.common.Resource
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow


interface AuthenticationRepository {
    suspend fun logIn(email: String, password: String): Flow<Resource<AuthResult>>
    suspend fun signUp(email: String, password: String): Flow<Resource<AuthResult>>
////    suspend fun logOut(): Flow<Resource<User>>
}