package com.example.creamon_midterm_app.presentation.state.store_item

import com.example.creamon_midterm_app.domain.model.store_items.StoreItem

data class StoreItemState(
    val isLoading: Boolean = true,
    val data: StoreItem? = null,
    val errorMessage: String? = null
)