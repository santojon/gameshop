package com.santojon.shop.ui.main.viewmodel

import com.santojon.shop.base.BaseViewModel
import com.santojon.shop.repository.UserRepository
import com.santojon.shop.room.entity.User
import org.kodein.di.generic.instance

/**
 * Binds View and Model classes
 */
class RegisterViewModel : BaseViewModel<RegisterViewModel>() {
    private val userRepository by instance<UserRepository>()

    /**
     * Get data from Database to View
     */
    fun createUser(user: User) = userRepository.createUser(user)
}