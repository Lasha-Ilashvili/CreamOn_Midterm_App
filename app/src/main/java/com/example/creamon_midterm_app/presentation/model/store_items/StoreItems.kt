package com.example.creamon_midterm_app.presentation.model.store_items

data class StoreItems(
    val iceCreams: List<StoreItem>,
    val sorbets: List<StoreItem>,
    val yogurts: List<StoreItem>
) {
    val storeItems = listOf(
        listOf(StoreItem(title = "Ice Creams")),
        iceCreams,
        listOf(StoreItem(title = "Sorbets")),
        sorbets,
        listOf(StoreItem(title = "Yogurt Base")),
        yogurts
    )
}