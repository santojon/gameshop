package com.santojon.shop.ui.main.viewmodel

import com.santojon.shop.base.BaseViewModel
import com.santojon.shop.repository.OrderRepository
import com.santojon.shop.repository.ProductRepository
import com.santojon.shop.room.entity.Order
import com.santojon.shop.room.entity.User
import org.kodein.di.generic.instance
import java.util.*

/**
 * Binds View and Model classes
 */
class OrderViewModel : BaseViewModel<OrderViewModel>() {
    private val orderRepository by instance<OrderRepository>()
    private val productRepository by instance<ProductRepository>()

    /**
     * Save data to database
     *
     * @param order: the order to save
     */
    fun saveOrder(order: Order) {
        order.date = Date()
        order.status = "Complete"
        orderRepository.saveOrder(order)
    }

    /**
     * Get orders of an user
     *
     * @param user: the user to get the orders
     * @param max: limit to query data
     */
    fun getOrderList(user: User, max: Int) = orderRepository.getUserOrderList(user.id, max)

    /**
     * Get products of an order
     *
     * @param order: the order to get the products
     * @param max: limit to query data
     */
    fun getOrderProductList(order: Order, max: Int) = productRepository.getOrderProductList(order.id, max)
}