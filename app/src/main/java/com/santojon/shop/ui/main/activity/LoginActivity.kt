package com.santojon.shop.ui.main.activity

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import com.santojon.shop.R
import com.santojon.shop.base.BaseActivity
import com.santojon.shop.room.entity.User
import com.santojon.shop.ui.main.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.activity_login.*

/**
 * Login Activity
 */
class LoginActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ViewModel provider register
        loginModel = ViewModelProviders.of(this).get(LoginViewModel::class.java).init(kodein)

        // Layout
        setContentView(R.layout.activity_login)
        title = resources.getText(R.string.login)

        // Login button function
        login_bt.setOnClickListener {
            login(email.text.toString(), password.text.toString())
        }

        // Register click function
        register.setOnClickListener {
            showCreate()
        }
    }

    /**
     * Log in and go to MainActivity
     *
     * @param email: email given on login
     * @param password: password given on login
     */
    fun login(email: String, password: String) {
        var dbUser: User = loginModel.getUserFromCredentials(email, password)

        if (dbUser?.email != null) {
            // Update current user
            loginModel.setUserEmail(dbUser.email)
            loginModel.setUserPassword(dbUser.password)

            var intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            var toast = Toast.makeText(applicationContext, resources.getText(R.string.incorret_credentials), Toast.LENGTH_LONG)
            toast.setGravity(Gravity.CENTER, 0, 0)
            toast.show()
        }
    }

    /**
     * Go to Register
     */
    private fun showCreate() {
        var intent = Intent(applicationContext, RegisterActivity::class.java)
        startActivity(intent)
        finish()
    }
}