package com.example.creamon_midterm_app.data.service.authentication

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.userProfileChangeRequest

class AuthenticationServiceImpl(private val firebaseAuth: FirebaseAuth) : AuthenticationService {
    override suspend fun logIn(email: String, password: String): Task<AuthResult> {
        return firebaseAuth.signInWithEmailAndPassword(email, password)
    }

    override suspend fun signUp(
        email: String,
        password: String,
        userName: String
    ): Task<AuthResult> {
        return firebaseAuth.createUserWithEmailAndPassword(email, password).apply {
            result.user?.updateProfile(
                userProfileChangeRequest {
                    displayName = userName
                }
            )
        }
    }
}