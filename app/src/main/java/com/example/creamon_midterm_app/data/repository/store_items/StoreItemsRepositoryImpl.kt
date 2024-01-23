package com.example.creamon_midterm_app.data.repository.store_items

import com.example.creamon_midterm_app.data.common.HandleResponse
import com.example.creamon_midterm_app.data.common.Resource
import com.example.creamon_midterm_app.data.mapper.base.asResource
import com.example.creamon_midterm_app.data.mapper.store_items.toDomain
import com.example.creamon_midterm_app.data.service.store_items.StoreItemsService
import com.example.creamon_midterm_app.domain.model.store_items.GetStoreItems
import com.example.creamon_midterm_app.domain.repository.store_items.StoreItemsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class StoreItemsRepositoryImpl @Inject constructor(
    private val storeItemsService: StoreItemsService,
    private val handleResponse: HandleResponse,
) : StoreItemsRepository {

    override suspend fun getStoreItems(): Flow<Resource<GetStoreItems>> {
        return handleResponse.safeApiCall {
            storeItemsService.getStoreItems()
        }.asResource {
            it.toDomain()
        }
    }
}
