package com.example.creamon_midterm_app.presentation.state.store_items

import com.example.creamon_midterm_app.domain.model.store_items.StoreItems

data class StoreItemsState(
    val isLoading: Boolean = true,
    val data: StoreItems? = null,
    val errorMessage: String? = null
)