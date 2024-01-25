package com.example.creamon_midterm_app.domain.usecase.store_item

import com.example.creamon_midterm_app.domain.model.store_items.Category
import com.example.creamon_midterm_app.domain.model.store_items.StoreItem
import javax.inject.Inject

class GetStoreItemUseCase @Inject constructor() {
    operator fun invoke(id: Int, categories: List<Category>): StoreItem? {
        val itemList = categories.flatMap { category ->
            category.items
        }
        return itemList.find { storeItem ->
            storeItem.id == id
        }
    }
}
