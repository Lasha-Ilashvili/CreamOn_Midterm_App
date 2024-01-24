package com.example.creamon_midterm_app.presentation.screen.item_page

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.creamon_midterm_app.data.common.Resource
import com.example.creamon_midterm_app.domain.usecase.store_item.GetStoreItemUseCase
import com.example.creamon_midterm_app.presentation.event.store_item.StoreItemEvent
import com.example.creamon_midterm_app.presentation.state.store_item.StoreItemState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemPageViewModel @Inject constructor(
    private val storeItemUseCase: GetStoreItemUseCase
) : ViewModel() {

    private val _storeItem = MutableStateFlow(StoreItemState())
    val storeItem get() = _storeItem.asStateFlow()

    fun onEvent(event: StoreItemEvent) {
        when (event) {
            is StoreItemEvent.SetItem -> setItem(event.id)

            is StoreItemEvent.ResetErrorMessage -> updateErrorMessage()
        }
    }

    private fun setItem(id: Int) {
        viewModelScope.launch {
            storeItemUseCase(id).collect {
                when (it) {
                    is Resource.Success -> {
                        _storeItem.update { currentState ->
                            val itemList = it.data.categories.flatMap { category ->
                                category.items
                            }

                            val item = itemList.find { storeItem ->
                                storeItem.id == id
                            }

                            currentState.copy(
                                data = item
                            )
                        }
                    }

                    is Resource.Error -> updateErrorMessage(message = it.errorMessage)

                    is Resource.Loading -> _storeItem.update { currentState ->
                        currentState.copy(isLoading = it.loading)
                    }
                }
            }
        }
    }

    private fun updateErrorMessage(message: String? = null) {
        _storeItem.update { currentState ->
            currentState.copy(errorMessage = message)
        }
    }
}