package com.santojon.shop.base

import android.annotation.SuppressLint
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.santojon.shop.ui.main.viewmodel.LoginViewModel
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.KodeinTrigger
import org.kodein.di.android.closestKodein


/**
 * Base Activity for all needed ones
 */
@SuppressLint("Registered")
abstract class BaseActivity : AppCompatActivity(), KodeinAware {
    protected lateinit var loginModel: LoginViewModel

    // Dependency injection
    override val kodein: Kodein by closestKodein()
    override val kodeinTrigger = KodeinTrigger()

    /**
     * Whe activity is created
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        kodeinTrigger.trigger()


        // ViewModel provider register
        loginModel = ViewModelProviders.of(this).get(LoginViewModel::class.java).init(kodein)
    }
}