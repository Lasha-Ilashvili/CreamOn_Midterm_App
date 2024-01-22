package com.example.creamon_midterm_app.data.service.authentication

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult

interface AuthenticationService {
    suspend fun logIn(email: String, password: String): Task<AuthResult>
    suspend fun signUp(email: String, password: String): Task<AuthResult>
////    suspend fun logOut(): Task<AuthResult>
}

