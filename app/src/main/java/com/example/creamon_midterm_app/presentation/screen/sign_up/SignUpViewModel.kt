package com.example.creamon_midterm_app.presentation.screen.sign_up

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.creamon_midterm_app.data.common.Resource
import com.example.creamon_midterm_app.domain.repository.authentication.AuthenticationRepository
import com.example.creamon_midterm_app.presentation.authentication.AuthenticationState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val authenticationRepository: AuthenticationRepository
) : ViewModel() {

    private val _signUpState = MutableStateFlow(AuthenticationState())
    val signUpState get() = _signUpState

    fun signUp(email: String, password: String, userName: String) {
        viewModelScope.launch {
            authenticationRepository.signUp(email, password, userName).collect {
                when (it) {

                    is Resource.Success -> {
                        _signUpState.update { currentState ->
                            currentState.copy(user = it.data)
                        }
//                        _uiEvent.emit(LogInUiEvent.NavigateToConnections)
                    }

                    is Resource.Error -> _signUpState.update { currentState ->
                        currentState.copy(errorMessage = it.errorMessage)
                    }

                    is Resource.Loading -> _signUpState.update { currentState ->
                        currentState.copy(isLoading = it.loading)
                    }
                }
            }
        }
    }
}