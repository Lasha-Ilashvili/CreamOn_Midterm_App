package com.example.creamon_midterm_app.data.mapper.store_items

import com.example.creamon_midterm_app.data.model.StoreItemDto
import com.example.creamon_midterm_app.domain.model.store_items.StoreItem

fun StoreItemDto.toDomain() =
    StoreItem(
        id = id,
        image = image,
        description = description,
        price = price,
        title = title
    )