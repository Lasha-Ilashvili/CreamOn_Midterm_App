package com.example.creamon_midterm_app.data.mapper.store_items

import com.example.creamon_midterm_app.data.model.CategoryDto
import com.example.creamon_midterm_app.data.model.StoreItemDto
import com.example.creamon_midterm_app.data.model.StoreItemsDto
import com.example.creamon_midterm_app.domain.model.store_items.Category
import com.example.creamon_midterm_app.domain.model.store_items.StoreItem
import com.example.creamon_midterm_app.domain.model.store_items.StoreItems

fun StoreItemDto.toDomain(): StoreItem {
    return StoreItem(
        id = id,
        image = image,
        description = description,
        price = price,
        title = title
    )
}


fun CategoryDto.toDomain(): Category {
    return Category(
        title = title,
        items = items.map {
            it.toDomain()
        }
    )
}

fun StoreItemsDto.toDomain(): StoreItems {
    return StoreItems(
        categories = categories.map {
            it.toDomain()
        }
    )
}