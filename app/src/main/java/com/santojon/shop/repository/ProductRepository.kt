package com.santojon.shop.repository

import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import android.content.Context
import com.santojon.api.Api
import com.santojon.shop.base.BaseRepository
import com.santojon.shop.room.dao.ProductDao
import com.santojon.shop.room.entity.Product
import org.kodein.di.generic.instance

/**
 * Responsible to deals with {@link Product}s
 */
class ProductRepository(private val context: Context) : BaseRepository<ProductRepository>(context = context) {
    private val productDao by instance<ProductDao>()
    private val api by instance<Api>()

    /**
     * Get a Product from DAO
     *
     * @param id: product id
     */
    fun getProduct(id: Long) = productDao.getProduct(id)

    /**
     * Get List of ALL Products from DAO
     *
     * @param max: max page size
     */
    fun getProductList(max: Int) = LivePagedListBuilder(productDao.getProductList(max), pagingConfig).build()

    /**
     * Get List of Products of an Order from DAO
     *
     * @param orderId: id of order to get products from
     * @param max: max page size
     */
    fun getOrderProductList(orderId: Long, max: Int) = productDao.getOrderProductList(orderId, max)

    /**
     * Update data from API changes
     */
    fun fetchProductList() {
        api.getProductList().observeForever({ result ->
            if (result != null) {
                val (data) = result
                if (data != null)
                    productDao.updateProductList(Product.fromProductList(data))
            }
        })
    }
}