package com.santojon.shop.room.dao

import android.arch.paging.DataSource
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update
import com.santojon.shop.room.entity.User

/**
 * Data access Object for {@link User}s
 */
@Dao
interface UserDao {
    /**
     * List of Users
     */
    @Query("SELECT * FROM user LIMIT :max")
    fun getUserList(max: Int): DataSource.Factory<Int, User>

    /**
     * User by email/password
     */
    @Query("SELECT * FROM user WHERE user.email = :email AND user.password = :password LIMIT 1")
    fun getUser(email: String, password: String): User

    @Insert(onConflict = REPLACE)
    fun insertUser(user: User)

    @Insert(onConflict = REPLACE)
    fun updateUserList(list: List<User>)

    @Update(onConflict = REPLACE)
    fun updateUser(user: User)
}