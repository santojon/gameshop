package com.santojon.shop.room.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Represents a Model for user bind in database
 */
@Entity(tableName = "user")
class User {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long = 0L

    @ColumnInfo(name = "name")
    var name: String = ""

    @ColumnInfo(name = "address")
    var address: String = ""

    @ColumnInfo(name = "phone")
    var phone: String = ""

    @ColumnInfo(name = "email")
    var email: String = ""

    @ColumnInfo(name = "password")
    var password: String = ""

    /**
     * Empty constructor
     */
    constructor()

    /**
     * Constructor with data
     */
    constructor(name: String, address: String, phone: String, email: String, password: String): this() {
        this.name = name
        this.address = address
        this.phone = phone
        this.email = email
        this.password = password
    }
}