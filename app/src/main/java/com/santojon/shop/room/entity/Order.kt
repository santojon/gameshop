package com.santojon.shop.room.entity

import android.arch.persistence.room.*
import java.util.*
import android.arch.persistence.room.ColumnInfo


/**
 * Represents a Model for Orders bind in Database
 */
@Entity(tableName = "order", indices = [
    Index("user_id", "user_id")
], foreignKeys = [
    ForeignKey(entity = User::class, parentColumns = ["id"], childColumns = ["user_id"])
])
class Order {
    @ColumnInfo(name = "user_id")
    var user: Long = 0L

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long = 0L

    @ColumnInfo(name = "date")
    var date: Date = Date()

    @ColumnInfo(name = "status")
    var status: String = "open"

    @Ignore
    var products: MutableList<Product> = mutableListOf()
}

/**
 * Used to get {@link Order} with {@link Product}s
 */
@Entity(tableName = "order_product", indices = [
    Index("order_id", "order_id"),
    Index("product_id", "product_id")
], foreignKeys = [
    ForeignKey(entity = Order::class, parentColumns = ["id"], childColumns = ["order_id"]),
    ForeignKey(entity = Product::class, parentColumns = ["id"], childColumns = ["product_id"])
])
class OrderProduct {
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L

    @ColumnInfo(name = "order_id")
    var orderId: Long = 0L

    @ColumnInfo(name = "product_id")
    var productId: Long = 0L
}