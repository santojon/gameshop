package com.santojon.api

import android.util.Log
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.Request
import com.github.kittinunf.fuel.livedata.liveDataObject
import com.santojon.api.model.ProductListModel
import com.santojon.api.subapi.GamesApi

/**
 * The API to get the list of products from
 *
 * <p>
 *     Used to declare sources of data to populate App
 * </p>
 */
class Api {
    /**
     * Represents a request to External data
     *
     * @param api: A {@link FuelRouting} Class
     */
    private fun request(api: Fuel.RequestConvertible): Request {
        return Fuel.request(api)
                .timeout(30000)
    }

    /**
     * Get a list of products from API as {@link ProductListModel}
     */
    fun getProductList() = request(GamesApi.GamesList()).liveDataObject(ProductListModel.Deserializer())
}