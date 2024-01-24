package com.example.creamon_midterm_app.domain.usecase.store_item

import com.example.creamon_midterm_app.data.common.Resource
import com.example.creamon_midterm_app.domain.model.store_items.StoreItems
import com.example.creamon_midterm_app.domain.repository.store_items.StoreItemsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetStoreItemUseCase @Inject constructor(
    private val storeItemsRepository: StoreItemsRepository
) {
    suspend operator fun invoke(id: Int): Flow<Resource<StoreItems>> =
        storeItemsRepository.getStoreItems()
}