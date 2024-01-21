package com.example.creamon_midterm_app.presentation.authentication

import com.example.creamon_midterm_app.data.common.User

data class AuthenticationState(
    val isLoading: Boolean = false,
    val user: User? = null,
    val errorMessage: String? = null
)