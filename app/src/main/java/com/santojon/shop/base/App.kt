package com.santojon.shop.base

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import android.content.SharedPreferences
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.santojon.shop.room.AppDatabase
import com.santojon.api.Api
import com.santojon.shop.repository.OrderRepository
import com.santojon.shop.repository.ProductRepository
import com.santojon.shop.repository.UserRepository
import com.santojon.shop.room.dao.OrderDao
import com.santojon.shop.room.dao.ProductDao
import com.santojon.shop.room.dao.UserDao
import com.santojon.shop.room.entity.Order
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

/**
 * Create App data injections and bindings
 */
class App : Application(), KodeinAware {
    // Override dependecy injection values
    override val kodein = Kodein.lazy {
        val database: AppDatabase = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "app.db")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()

        bind<UserDao>() with singleton { database.userDao() }
        bind<UserRepository>() with singleton { UserRepository(applicationContext) }

        bind<ProductDao>() with singleton { database.productDao() }
        bind<ProductRepository>() with singleton { ProductRepository(applicationContext) }

        bind<OrderDao>() with singleton { database.orderDao() }
        bind<OrderRepository>() with singleton { OrderRepository(applicationContext) }

        bind<Api>() with singleton { Api() }
        bind<SharedPreferences>() with instance(getSharedPreferences("PREFERENCES", Context.MODE_PRIVATE))
    }

    companion object {
        var cart: Order = Order()
    }

    /**
     * Add logger on creation
     */
    override fun onCreate() {
        super.onCreate()
        Logger.addLogAdapter(AndroidLogAdapter())
    }
}