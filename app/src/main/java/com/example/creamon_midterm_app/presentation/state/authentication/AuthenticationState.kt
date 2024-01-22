package com.example.creamon_midterm_app.presentation.state.authentication

import com.google.firebase.auth.AuthResult

data class AuthenticationState(
    val isLoading: Boolean = false,
    val result: AuthResult? = null,
    val errorMessage: String? = null
)