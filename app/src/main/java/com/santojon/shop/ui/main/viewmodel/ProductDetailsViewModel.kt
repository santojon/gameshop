package com.santojon.shop.ui.main.viewmodel

import com.santojon.shop.base.BaseViewModel
import com.santojon.shop.repository.ProductRepository
import com.santojon.shop.room.entity.Product
import org.kodein.di.generic.instance

/**
 * Binds View and Model classes
 */
class ProductDetailsViewModel : BaseViewModel<ProductDetailsViewModel>() {
    private val productRepository by instance<ProductRepository>()
    private var product : Product = Product()

    /**
     * Get data from Database to View
     */
    fun getProduct(id: Long): Product {
        product = productRepository.getProduct(id)
        return product
    }
}