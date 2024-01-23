package com.example.creamon_midterm_app.data.mapper.store_items

import com.example.creamon_midterm_app.data.model.StoreItemDto
import com.example.creamon_midterm_app.data.model.StoreItemsDto
import com.example.creamon_midterm_app.domain.model.store_items.GetStoreItem
import com.example.creamon_midterm_app.domain.model.store_items.GetStoreItems

fun StoreItemDto.toDomain(): GetStoreItem {
    return GetStoreItem(
        id = id,
        image = image,
        description = description,
        price = price,
        title = title
    )
}

fun StoreItemsDto.toDomain(): GetStoreItems {
    return GetStoreItems(
        iceCreams = iceCreams.map { it.toDomain() },
        sorbets = sorbets.map { it.toDomain() },
        yogurts = yogurts.map { it.toDomain() }
    )
}