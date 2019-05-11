package com.santojon.shop.base

import android.arch.lifecycle.ViewModel
import android.content.SharedPreferences
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.KodeinTrigger
import org.kodein.di.generic.instance

/**
 * Base ViewModel for all needed ones
 *
 * <p>
 *     This class inject kodein defined scopes into self when extended
 * </p>
 */
abstract class BaseViewModel<out T> : ViewModel(), KodeinAware {
    // Dependency injection
    override lateinit var kodein: Kodein
    override val kodeinTrigger: KodeinTrigger = KodeinTrigger()
    val sharedPreferences by instance<SharedPreferences>()

    @Suppress("UNCHECKED_CAST")
    fun init(kodein: Kodein): T {
        this.kodein = kodein
        kodeinTrigger.trigger()
        return this as T
    }

    /**
     * Get user email property from shared preferences
     */
    fun getUseremail(): String {
        return sharedPreferences.getString("email", "")
    }

    /**
     * Get user password property from shared preferences
     */
    fun getUserPassword(): String {
        return sharedPreferences.getString("password", "")
    }

    /**
     * Set user email property into shared preferences
     */
    fun setUserEmail(value: String) {
        sharedPreferences.edit().putString("email", value).apply()
    }

    /**
     * Set user password property into shared preferences
     */
    fun setUserPassword(value: String) {
        sharedPreferences.edit().putString("password", value).apply()
    }
}