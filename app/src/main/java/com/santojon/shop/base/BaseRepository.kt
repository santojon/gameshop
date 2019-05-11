package com.santojon.shop.base

import android.arch.paging.PagedList
import android.content.Context
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.KodeinTrigger
import org.kodein.di.android.closestKodein

/**
 * Base Repository for all needed ones
 */
abstract class BaseRepository<out T>(context: Context) : KodeinAware {

    // Dependency injection
    override val kodein: Kodein by closestKodein(context)
    override val kodeinTrigger: KodeinTrigger = KodeinTrigger()

    // List page configuration
    val pagingConfig = PagedList.Config.Builder()
            .setPageSize(6)
            .build()

    @Suppress("UNCHECKED_CAST")
    fun init(): T {
        kodeinTrigger.trigger()
        return this as T
    }
}