package com.example.creamon_midterm_app.data.model


import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CategoryDto(
    val title: String,
    val items: List<StoreItemDto>
)