package com.example.creamon_midterm_app.domain.repository.authentication

import com.example.creamon_midterm_app.data.common.Resource
import com.example.creamon_midterm_app.data.common.User
import kotlinx.coroutines.flow.Flow


interface AuthenticationRepository {
    suspend fun logIn(email: String, password: String): Flow<Resource<User>>
    suspend fun signUp(email: String, password: String, userName: String): Flow<Resource<User>>
//    suspend fun logOut(): Flow<Resource<User>>
}