package com.example.creamon_midterm_app.presentation.screen.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val firebaseAuth: FirebaseAuth) :
    ViewModel() {

    private val _uiEvent = MutableSharedFlow<SplashUiEvent>()
    val uiEvent: SharedFlow<SplashUiEvent> get() = _uiEvent

    init {
        readSession()
    }

    private fun readSession() {
        viewModelScope.launch {
            _uiEvent.emit(
                if (firebaseAuth.currentUser == null)
                    SplashUiEvent.NavigateToWelcomePage
                else
                    SplashUiEvent.NavigateToMainPage
            )
        }
    }

    sealed interface SplashUiEvent {
        data object NavigateToMainPage : SplashUiEvent
        data object NavigateToWelcomePage : SplashUiEvent
    }
}