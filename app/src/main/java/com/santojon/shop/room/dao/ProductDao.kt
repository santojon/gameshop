package com.santojon.shop.room.dao

import android.arch.paging.DataSource
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update
import com.santojon.shop.room.entity.Product

/**
 * Data access Object for {@link Product}s
 */
@Dao
interface ProductDao {
    /**
     * Get a single product
     */
    @Query("SELECT * FROM product WHERE product.id = :id LIMIT 1")
    fun getProduct(id: Long): Product

    /**
     * List of Products
     */
    @Query("SELECT * FROM product LIMIT :max")
    fun getProductList(max: Int): DataSource.Factory<Int, Product>

    /**
     * List of Products from an Order
     */
    @Query("SELECT product.id, name, description, price, image FROM product INNER JOIN order_product ON order_product.order_id = :orderId LIMIT :max")
    fun getOrderProductList(orderId: Long, max: Int): MutableList<Product>

    @Insert(onConflict = REPLACE)
    fun insertProduct(product: Product)

    @Insert(onConflict = REPLACE)
    fun updateProductList(list: List<Product>)

    @Update(onConflict = REPLACE)
    fun updateProduct(product: Product)
}