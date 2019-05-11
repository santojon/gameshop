package com.santojon.shop.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.santojon.shop.room.converters.DateConverter
import com.santojon.shop.room.dao.OrderDao
import com.santojon.shop.room.dao.ProductDao
import com.santojon.shop.room.dao.UserDao
import com.santojon.shop.room.entity.Order
import com.santojon.shop.room.entity.OrderProduct
import com.santojon.shop.room.entity.Product
import com.santojon.shop.room.entity.User

/**
 * Represents the connection to database using a {@link Room} DAO
 */
@Database(
        entities = [
            (User::class),
            (Product::class),
            (Order::class),
            (OrderProduct::class)
        ],
        version = 5,
        exportSchema = false
)
@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
    abstract fun userDao(): UserDao
    abstract fun orderDao(): OrderDao
}