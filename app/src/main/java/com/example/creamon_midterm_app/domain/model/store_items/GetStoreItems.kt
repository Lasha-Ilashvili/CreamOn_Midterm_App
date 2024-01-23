package com.example.creamon_midterm_app.domain.model.store_items

data class GetStoreItems(
    val iceCreams: List<GetStoreItem>,
    val sorbets: List<GetStoreItem>,
    val yogurts: List<GetStoreItem>
)