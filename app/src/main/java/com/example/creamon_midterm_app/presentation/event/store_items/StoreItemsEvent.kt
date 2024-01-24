package com.example.creamon_midterm_app.presentation.event.store_items

sealed class StoreItemsEvent {
    data object LogOut : StoreItemsEvent()
    data object ResetErrorMessage : StoreItemsEvent()
}