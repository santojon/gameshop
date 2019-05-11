package com.santojon.api.subapi

import com.github.kittinunf.fuel.core.Method
import com.github.kittinunf.fuel.util.FuelRouting

/**
 * An API data source
 *
 * <p>
 *     Used to populate data to database
 * </p>
 */
internal sealed class GamesApi : FuelRouting {
    class GamesList : GamesApi()

    // Base path for request
    override val basePath: String = "http://api.myjson.com/"

    // Headers
    override val headers: Map<String, String>? = null

    // Method of the request
    override val method: Method
        get() {
            when (this) {
                is GamesList -> return Method.GET
            }
        }

    // Parameters
    override val params: List<Pair<String, Any?>>?
        get() {
            when (this) {
                is GamesList -> return null
            }
        }

    // Path of the request
    override val path: String
        get() {
            when (this) {
                is GamesList -> return "bins/10gtpq"
            }
        }
}