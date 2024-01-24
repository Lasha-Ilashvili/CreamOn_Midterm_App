package com.example.creamon_midterm_app.presentation.event.store_item

sealed class StoreItemEvent {
    data class SetItem(val id: Int) : StoreItemEvent()
    data object ResetErrorMessage : StoreItemEvent()
}