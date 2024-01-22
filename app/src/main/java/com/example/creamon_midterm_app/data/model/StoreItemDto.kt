package com.example.creamon_midterm_app.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class StoreItemDto(
    @Json(name = "id")
    val id: String? = null,
    @Json(name = "image")
    val image: String? = null,
    @Json(name = "description")
    val description: String? = null,
    @Json(name = "price")
    val price: Double? = null,
    @Json(name = "title")
    val title: String
)
