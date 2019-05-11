package com.santojon.shop.repository

import android.arch.paging.LivePagedListBuilder
import android.content.Context
import com.santojon.shop.base.BaseRepository
import com.santojon.shop.room.dao.OrderDao
import com.santojon.shop.room.entity.Order
import com.santojon.shop.room.entity.OrderProduct
import org.kodein.di.generic.instance

/**
 * Responsible to deals with {@link Order}s
 */
class OrderRepository(private val context: Context) : BaseRepository<OrderRepository>(context = context) {
    private val orderDao by instance<OrderDao>()

    fun saveOrder(order: Order) {
        var orderId = orderDao.insertOrder(order)
        order.id = orderId
        insertRelations(order)
    }

    fun insertRelations(order: Order) {
        order.products.forEach { product ->
            var orderProduct = OrderProduct()
            orderProduct.orderId = order.id
            orderProduct.productId = product.id

            orderDao.inserOrderProduct(orderProduct)
        }
    }

    /**
     * Get List of ALL Orders from DAO
     *
     * @param max: max page size
     */
    fun getOrderList(max: Int) = LivePagedListBuilder(orderDao.getOrderList(max), pagingConfig).build()

    /**
     * Get List of Orders of an User from DAO
     *
     * @param userId: id of user to get orders from
     * @param max: max page size
     */
    fun getUserOrderList(userId: Long, max: Int) = LivePagedListBuilder(orderDao.getUserOrderList(userId, max), pagingConfig).build()
}