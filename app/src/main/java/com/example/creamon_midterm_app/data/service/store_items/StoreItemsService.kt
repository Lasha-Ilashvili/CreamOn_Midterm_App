package com.example.creamon_midterm_app.data.service.store_items

import com.example.creamon_midterm_app.data.model.StoreItemsDto
import retrofit2.Response
import retrofit2.http.GET

interface StoreItemsService {
    @GET("b92c00a6-5a4d-4a17-8c12-ab6d578c57ed")
    suspend fun getStoreItems(): Response<StoreItemsDto>
}