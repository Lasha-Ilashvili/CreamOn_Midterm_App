package com.example.creamon_midterm_app.domain.repository.store_items

import com.example.creamon_midterm_app.data.common.Resource
import com.example.creamon_midterm_app.domain.model.store_items.StoreItem
import kotlinx.coroutines.flow.Flow

interface StoreItemsRepository {
    suspend fun getStoreItems(): Flow<Resource<List<List<StoreItem>>>>
}