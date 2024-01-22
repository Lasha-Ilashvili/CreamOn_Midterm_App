package com.example.creamon_midterm_app.presentation.state.store_items

import com.example.creamon_midterm_app.domain.model.store_items.StoreItem

data class StoreItemsState(
    val isLoading: Boolean = false,
    val result: List<List<StoreItem>>? = null,
    val errorMessage: String? = null
)