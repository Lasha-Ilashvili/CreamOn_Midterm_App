package com.example.creamon_midterm_app.presentation.screen.main_page

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.creamon_midterm_app.data.common.Resource
import com.example.creamon_midterm_app.domain.model.store_items.StoreItem
import com.example.creamon_midterm_app.domain.usecase.store_items.StoreItemsUseCase
import com.example.creamon_midterm_app.presentation.event.store_items.StoreItemsEvent
import com.example.creamon_midterm_app.presentation.state.store_items.StoreItemsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainPageViewModel @Inject constructor(
    private val storeItemsUseCase: StoreItemsUseCase
) : ViewModel() {

    private val _storeItems = MutableStateFlow(StoreItemsState())
    val storeItems get() = _storeItems.asStateFlow()

    private val _uiEvent = MutableSharedFlow<MainPageUiEvent>()
    val uiEvent get() = _uiEvent.asSharedFlow()

    init {
        setInitialList()
    }

    fun onEvent(event: StoreItemsEvent) {
        when (event) {
            is StoreItemsEvent.ResetErrorMessage -> updateErrorMessage()
        }
    }

    private fun setInitialList() {
        viewModelScope.launch {
            storeItemsUseCase().collect {
                when (it) {
                    is Resource.Success -> {
                        _storeItems.update { currentState ->
                            currentState.copy(result = it.data)
                        }

//                        _uiEvent.emit(
//                            SignUpViewModel.SignUpUiEvent.NavigateBackToLogIn(
//
//                            )
//                        )
                    }

                    is Resource.Error -> updateErrorMessage(message = it.errorMessage)

                    is Resource.Loading -> _storeItems.update { currentState ->
                        currentState.copy(isLoading = it.loading)
                    }
                }
            }
        }
    }

    private fun updateErrorMessage(message: String? = null) {
        _storeItems.update { currentState ->
            currentState.copy(errorMessage = message)
        }
    }

    sealed interface MainPageUiEvent {
        data class NavigateBackToLogIn(val item: StoreItem) : MainPageUiEvent
    }
}