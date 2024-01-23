package com.example.creamon_midterm_app.presentation.screen.mapper.store_items

import com.example.creamon_midterm_app.domain.model.store_items.GetStoreItem
import com.example.creamon_midterm_app.domain.model.store_items.GetStoreItems
import com.example.creamon_midterm_app.presentation.model.store_items.StoreItem
import com.example.creamon_midterm_app.presentation.model.store_items.StoreItems

fun GetStoreItem.toPresentation(): StoreItem {
    return StoreItem(
        id = id,
        image = image,
        description = description,
        price = price,
        title = title
    )
}

fun GetStoreItems.toPresentation(): StoreItems {
    return StoreItems(
        iceCreams = iceCreams.map { it.toPresentation() },
        sorbets = sorbets.map { it.toPresentation() },
        yogurts = yogurts.map { it.toPresentation() }
    )
}