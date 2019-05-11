package com.santojon.shop.ui.main.activity

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import com.santojon.shop.R
import com.santojon.shop.base.BaseActivity
import com.santojon.shop.room.entity.User
import com.santojon.shop.ui.main.viewmodel.RegisterViewModel
import kotlinx.android.synthetic.main.activity_register.*

/**
 * Register Activity
 */
class RegisterActivity : BaseActivity() {
    private lateinit var model: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        title = resources.getText(R.string.register_user)

        // ViewModel provider register
        model = ViewModelProviders.of(this).get(RegisterViewModel::class.java).init(kodein)

        // Register button action
        create.setOnClickListener {
            // Register a new User
            register(
                    name_reg.text.toString(),
                    address_reg.text.toString(),
                    phone_reg.text.toString(),
                    email_reg.text.toString(),
                    password_reg.text.toString(),
                    password_confirm_reg.text.toString()
            )
        }
    }

    /**
     * Register user and go to MainActivity
     *
     * <p>
     *     Params from screen to create a new User
     * </p>
     *
     * @param name
     * @param address
     * @param phone
     * @param email
     * @param password
     * @param passConfim
     */
    fun register(name: String, address: String, phone: String, email: String, password: String, passConfim: String) {
        if (password == passConfim) {
            var user = User(name, address, phone, email, password)
            model.createUser(user)

            // Update current user
            model.setUserEmail(user.email)
            model.setUserPassword(user.password)

            // Go to main activity
            var intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            var toast = Toast.makeText(applicationContext, resources.getText(R.string.incorret_confirmation), Toast.LENGTH_LONG)
            toast.setGravity(Gravity.CENTER, 0, 0)
            toast.show()
        }
    }
}