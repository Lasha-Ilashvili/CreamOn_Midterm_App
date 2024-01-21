package com.example.creamon_midterm_app.data.repository.authentication

import com.example.creamon_midterm_app.data.common.HandleResponse
import com.example.creamon_midterm_app.data.common.Resource
import com.example.creamon_midterm_app.data.common.User
import com.example.creamon_midterm_app.data.service.authentication.AuthenticationService
import com.example.creamon_midterm_app.domain.repository.authentication.AuthenticationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class AuthenticationRepositoryImpl @Inject constructor(
    private val authenticationService: AuthenticationService,
    private val handleResponse: HandleResponse,
) : AuthenticationRepository {

    override suspend fun logIn(email: String, password: String): Flow<Resource<User>> {
        return flow {
            handleResponse.safeAuthCall {
                authenticationService.logIn(email, password)
            }
        }
    }

    override suspend fun signUp(
        email: String,
        password: String,
        userName: String
    ): Flow<Resource<User>> {
        return flow {
            handleResponse.safeAuthCall {
                authenticationService.signUp(email, password, userName)
            }
        }
    }
}