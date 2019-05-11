package com.santojon.shop.room.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.santojon.api.model.ProductListModel
import com.santojon.api.model.ProductModel

/**
 * Represents a Model for products bind in database
 */
@Entity(tableName = "product")
class Product {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long = 0L

    @ColumnInfo(name = "name")
    var name: String = ""

    @ColumnInfo(name = "description")
    var description: String = ""

    @ColumnInfo(name = "price")
    var price: Float = 0.0F

    @ColumnInfo(name = "image")
    var image: String = ""

    /**
     * Create a product from API model data {@link ProductModel}
     */
    fun fromData(product: ProductModel): Product {
        id = product.id
        name = product.name
        description = product.description
        price = product.price
        image = product.image

        return this
    }

    /**
     * List of products
     */
    companion object {
        fun fromProductList(productListModel: ProductListModel): List<Product> =
                productListModel.listOfProductData.map { data -> Product().fromData(data) }

    }
}