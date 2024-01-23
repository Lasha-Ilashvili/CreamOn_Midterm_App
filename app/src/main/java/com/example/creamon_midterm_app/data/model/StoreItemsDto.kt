package com.example.creamon_midterm_app.data.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class StoreItemsDto(
    @Json(name = "ice_creams")
    val iceCreams: List<StoreItemDto>,
    @Json(name = "sorbets")
    val sorbets: List<StoreItemDto>,
    @Json(name = "yogurts")
    val yogurts: List<StoreItemDto>
)