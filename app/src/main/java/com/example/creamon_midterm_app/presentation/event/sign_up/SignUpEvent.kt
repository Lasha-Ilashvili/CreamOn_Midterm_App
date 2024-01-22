package com.example.creamon_midterm_app.presentation.event.sign_up

sealed class SignUpEvent {
    data class SignUp(val email: String, val password: String) : SignUpEvent()
    data object ResetErrorMessage : SignUpEvent()
}