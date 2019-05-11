package com.santojon.shop.ui.main.viewmodel

import com.santojon.shop.base.BaseViewModel
import com.santojon.shop.repository.UserRepository
import com.santojon.shop.room.entity.User
import org.kodein.di.generic.instance

/**
 * Binds View and Model classes
 */
class LoginViewModel : BaseViewModel<LoginViewModel>() {
    private val userRepository by instance<UserRepository>()
    private var user: User = User()

    /**
     * Get data from Database to View
     */
    fun getUserFromCredentials(email: String, password: String) = userRepository.getUser(email, password)

    /**
     * Get data from Database to View using sharedPreferences data
     */
    fun getUserFromPreferences() = getUserFromCredentials(getUseremail(), getUserPassword())
}