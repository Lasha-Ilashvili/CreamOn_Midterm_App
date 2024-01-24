package com.example.creamon_midterm_app.presentation.screen.main_page

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.creamon_midterm_app.data.common.Resource
import com.example.creamon_midterm_app.domain.usecase.store_items.GetStoreItemsUseCase
import com.example.creamon_midterm_app.presentation.event.store_items.StoreItemsEvent
import com.example.creamon_midterm_app.presentation.state.store_items.StoreItemsState
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainPageViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val storeItemsUseCase: GetStoreItemsUseCase
) : ViewModel() {

    private val _storeItems = MutableStateFlow(StoreItemsState())
    val storeItems get() = _storeItems.asStateFlow()

    private val _uiEvent = MutableSharedFlow<MainPageUiEvent>()
    val uiEvent: SharedFlow<MainPageUiEvent> get() = _uiEvent

    init {
        setInitialList()
    }

    fun onEvent(event: StoreItemsEvent) {
        when (event) {
            is StoreItemsEvent.LogOut -> logOut()
            is StoreItemsEvent.ResetErrorMessage -> updateErrorMessage()
        }
    }

    private fun setInitialList() {
        viewModelScope.launch {
            storeItemsUseCase().collect {
                when (it) {
                    is Resource.Success -> {
                        _storeItems.update { currentState ->
                            currentState.copy(data = it.data)
                        }
                    }

                    is Resource.Error -> updateErrorMessage(message = it.errorMessage)

                    is Resource.Loading -> _storeItems.update { currentState ->
                        currentState.copy(isLoading = it.loading)
                    }
                }
            }
        }
    }

    private fun logOut() {
        viewModelScope.launch {
            firebaseAuth.signOut()
            _uiEvent.emit(MainPageUiEvent.NavigateBackToWelcomePage)
        }
    }

    private fun updateErrorMessage(message: String? = null) {
        _storeItems.update { currentState ->
            currentState.copy(errorMessage = message)
        }
    }

    sealed interface MainPageUiEvent {
        data object NavigateBackToWelcomePage : MainPageUiEvent
    }
}