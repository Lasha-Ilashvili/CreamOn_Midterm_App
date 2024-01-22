package com.example.creamon_midterm_app.domain.usecase.authentication

import com.example.creamon_midterm_app.domain.repository.authentication.AuthenticationRepository
import javax.inject.Inject

class LogInUseCase @Inject constructor(
    private val authenticationRepository: AuthenticationRepository
) {
    suspend operator fun invoke(email: String, password: String) =
        authenticationRepository.logIn(email, password)
}