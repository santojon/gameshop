package com.santojon.api.model

import com.beust.klaxon.Json
import com.beust.klaxon.Klaxon
import com.github.kittinunf.fuel.core.ResponseDeserializable

/**
 * Represents a Product list
 *
 * <p>
 *     Used to populate products list with external data
 * </p>
 */
data class ProductListModel(
        @Json(name = "products") val listOfProductData: List<ProductModel> = listOf()
) {
    class Deserializer : ResponseDeserializable<ProductListModel> {
        override fun deserialize(content: String) = Klaxon().parse<ProductListModel>(content)
    }
}