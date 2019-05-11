package com.santojon.shop.room.dao

import android.arch.paging.DataSource
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update
import com.santojon.shop.room.entity.Order
import com.santojon.shop.room.entity.OrderProduct

/**
 * Data access Object for {@link Order}s
 */
@Dao
interface OrderDao {
    /**
     * List of Orders
     */
    @Query("SELECT * FROM `order` LIMIT :max")
    fun getOrderList(max: Int): DataSource.Factory<Int, Order>

    @Query("SELECT * FROM `order` WHERE `order`.id = :id LIMIT 1")
    fun getOrder(id: Long): Order

    /**
     * List of Orders from an User
     */
    @Query("SELECT * FROM `order` WHERE `order`.user_id = :userId LIMIT :max")
    fun getUserOrderList(userId: Long, max: Int): DataSource.Factory<Int, Order>

    @Insert(onConflict = REPLACE)
    fun insertOrder(order: Order): Long

    @Insert(onConflict = REPLACE)
    fun inserOrderProduct(orderProduct: OrderProduct)

    @Insert(onConflict = REPLACE)
    fun updateOrderList(list: List<Order>)

    @Update(onConflict = REPLACE)
    fun updateOrder(order: Order)
}