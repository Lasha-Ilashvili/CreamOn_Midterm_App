package com.example.creamon_midterm_app.domain.usecase.store_items

import com.example.creamon_midterm_app.domain.repository.store_items.StoreItemsRepository
import javax.inject.Inject

class GetStoreItemsUseCase @Inject constructor(
    private val storeItemsRepository: StoreItemsRepository
) {
    suspend operator fun invoke() =
        storeItemsRepository.getStoreItems()
}