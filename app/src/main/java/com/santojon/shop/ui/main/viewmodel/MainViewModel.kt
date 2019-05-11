package com.santojon.shop.ui.main.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.paging.PagedList
import com.santojon.shop.base.BaseViewModel
import com.santojon.shop.repository.ProductRepository
import com.santojon.shop.room.entity.Product
import org.kodein.di.generic.instance

/**
 * Binds View and Model classes
 */
class MainViewModel : BaseViewModel<MainViewModel>() {
    private val productRepository by instance<ProductRepository>()
    private var liveDaoProduct: LiveData<PagedList<Product>> = MutableLiveData()

    /**
     * Get data from Database to View
     */
    fun getProductList(max: Int): LiveData<PagedList<Product>> {
        liveDaoProduct = productRepository.getProductList(max)
        return liveDaoProduct
    }

    /**
     * Get 'firstLoad' property from shared preferences
     */
    fun isFirstLoad(): Boolean {
        return sharedPreferences.getBoolean("isFirstLoad", true)
    }

    /**
     * Set 'firstLoad' property in shared preferences
     */
    fun setFirstLoad(value: Boolean) {
        sharedPreferences.edit().putBoolean("isFirstLoad", value).apply()
    }

    /**
     * Fetch data from API to View
     */
    fun fetchProductList() = productRepository.fetchProductList()
}