package com.santojon.shop.ui.main.activity

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.santojon.shop.R
import com.santojon.shop.base.BaseActivity
import com.santojon.shop.ui.main.viewmodel.LoginViewModel

/**
 * Splash screen Activity
 */
class SplashActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        // ViewModel provider register
        loginModel = ViewModelProviders.of(this).get(LoginViewModel::class.java).init(kodein)
        var prefUser = loginModel.getUserFromPreferences()

        // Delay
        Handler().postDelayed({
            if (prefUser?.email != null) {
                var intent = Intent(applicationContext, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                showLogin()
            }
        }, 2000)
    }

    /**
     * Start Login Activity
     */
    private fun showLogin() {
        var intent = Intent(applicationContext, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}