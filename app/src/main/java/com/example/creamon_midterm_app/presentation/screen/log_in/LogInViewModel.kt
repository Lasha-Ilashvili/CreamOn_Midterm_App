package com.example.creamon_midterm_app.presentation.screen.log_in

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.creamon_midterm_app.data.common.Resource
import com.example.creamon_midterm_app.domain.usecase.authentication.LogInUseCase
import com.example.creamon_midterm_app.presentation.event.log_in.LogInEvent
import com.example.creamon_midterm_app.presentation.state.authentication.AuthenticationState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LogInViewModel @Inject constructor(
    private val logInUseCase: LogInUseCase
) : ViewModel() {

    private val _logInState = MutableStateFlow(AuthenticationState())
    val logInState get() = _logInState

    private val _uiEvent = MutableSharedFlow<LogInUiEvent>()
    val uiEvent: SharedFlow<LogInUiEvent> get() = _uiEvent

    fun onEvent(event: LogInEvent) {
        when (event) {
            is LogInEvent.LogIn -> logIn(event.email, event.password)
            is LogInEvent.ResetErrorMessage -> updateErrorMessage()
        }
    }

    private fun logIn(email: String, password: String) {
        viewModelScope.launch {
            logInUseCase(email, password).collect {
                when (it) {
                    is Resource.Success -> {
                        _logInState.update { currentState ->
                            currentState.copy(result = it.data)
                        }
                        _uiEvent.emit(LogInUiEvent.NavigateToMainPage)
                    }

                    is Resource.Error -> updateErrorMessage(message = it.errorMessage)

                    is Resource.Loading -> _logInState.update { currentState ->
                        currentState.copy(isLoading = it.loading)
                    }
                }
            }
        }
    }

    private fun updateErrorMessage(message: String? = null) {
        _logInState.update { currentState ->
            currentState.copy(errorMessage = message)
        }
    }

    sealed interface LogInUiEvent {
        data object NavigateToMainPage : LogInUiEvent
    }
}