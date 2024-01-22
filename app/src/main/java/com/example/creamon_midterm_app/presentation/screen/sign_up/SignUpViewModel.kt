package com.example.creamon_midterm_app.presentation.screen.sign_up

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.creamon_midterm_app.data.common.Resource
import com.example.creamon_midterm_app.domain.usecase.authentication.SignUpUseCase
import com.example.creamon_midterm_app.presentation.event.sign_up.SignUpEvent
import com.example.creamon_midterm_app.presentation.state.authentication.AuthenticationState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase
) : ViewModel() {

    private val _signUpState = MutableStateFlow(AuthenticationState())
    val signUpState get() = _signUpState

    private val _uiEvent = MutableSharedFlow<SignUpUiEvent>()
    val uiEvent: SharedFlow<SignUpUiEvent> get() = _uiEvent

    fun onEvent(event: SignUpEvent) {
        when (event) {
            is SignUpEvent.SignUp -> signUp(event.email, event.password)
            is SignUpEvent.ResetErrorMessage -> updateErrorMessage()
        }
    }

    private fun signUp(email: String, password: String) {
        viewModelScope.launch {
            signUpUseCase(email, password).collect {
                when (it) {
                    is Resource.Success -> {
                        _signUpState.update { currentState ->
                            currentState.copy(result = it.data)
                        }
                        _uiEvent.emit(SignUpUiEvent.NavigateBackToLogIn(email, password))
                    }

                    is Resource.Error -> updateErrorMessage(message = it.errorMessage)

                    is Resource.Loading -> _signUpState.update { currentState ->
                        currentState.copy(isLoading = it.loading)
                    }
                }
            }
        }
    }

    private fun updateErrorMessage(message: String? = null) {
        _signUpState.update { currentState ->
            currentState.copy(errorMessage = message)
        }
    }
}

sealed interface SignUpUiEvent {
    data class NavigateBackToLogIn(val email: String, val password: String) : SignUpUiEvent
}