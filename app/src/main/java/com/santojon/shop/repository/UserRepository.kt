package com.santojon.shop.repository

import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import android.content.Context
import android.util.Log
import com.santojon.api.Api
import com.santojon.shop.base.BaseRepository
import com.santojon.shop.room.dao.OrderDao
import com.santojon.shop.room.dao.ProductDao
import com.santojon.shop.room.dao.UserDao
import com.santojon.shop.room.entity.Product
import com.santojon.shop.room.entity.User
import org.kodein.di.generic.instance

/**
 * Responsible to deals with {@link User}s
 */
class UserRepository(private val context: Context) : BaseRepository<UserRepository>(context = context) {
    private val userDao by instance<UserDao>()

    /**
     * Get User from params
     *
     * @param email: user email
     * @param password: user password
     */
    fun getUser(email: String, password: String) = userDao.getUser(email, password)

    /**
     * Insert user into Database
     *
     * @param user: User to insert
     */
    fun createUser(user: User) = userDao.insertUser(user)
}