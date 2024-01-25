package com.example.creamon_midterm_app.data.model


import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class StoreItemsDto(
    val categories: List<CategoryDto>
)