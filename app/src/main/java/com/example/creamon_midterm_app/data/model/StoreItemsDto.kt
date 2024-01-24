package com.example.creamon_midterm_app.data.model


import com.squareup.moshi.JsonClass

//@JsonClass(generateAdapter = true)
//data class StoreItemsDto(
//    @Json(name = "ice_creams")
//    val iceCreams: List<StoreItemDto>,
//    @Json(name = "sorbets")
//    val sorbets: List<StoreItemDto>,
//    @Json(name = "yogurts")
//    val yogurts: List<StoreItemDto>
//)
@JsonClass(generateAdapter = true)
data class StoreItemsDto(
    val categories: List<CategoryDto>
)
/*
data class Category(
    val title: String,
    val items: List<StoreItem>
)

data class StoreItems(
    val categories: List<Category>
)
*/