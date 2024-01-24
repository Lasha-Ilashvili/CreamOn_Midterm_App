package com.example.creamon_midterm_app.data.service.store_items

import com.example.creamon_midterm_app.data.model.StoreItemsDto
import retrofit2.Response
import retrofit2.http.GET

interface StoreItemsService {
    @GET("7b635ae8-d58c-4a61-b124-f468af61e642")
    suspend fun getStoreItems(): Response<StoreItemsDto>
}