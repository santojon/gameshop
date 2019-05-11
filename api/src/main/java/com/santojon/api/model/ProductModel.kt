package com.santojon.api.model

import com.beust.klaxon.Json

/**
 * Represents a Product from API
 */
data class ProductModel(
        @Json(name = "id") var id: Long = 0L,
        @Json(name = "name") var name: String = "",
        @Json(name = "description") var description: String = "",
        @Json(name = "price") var price: Float = 0.0F,
        @Json(name = "image") var image: String = ""
)



