package com.santojon.shop.ui.main.activity

import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat
import com.santojon.shop.R
import com.santojon.shop.base.App
import com.santojon.shop.base.ShopEnableActivity
import com.santojon.shop.room.entity.Order
import com.santojon.shop.ui.main.fragment.CartFragment
import kotlinx.android.synthetic.main.activity_cart.*

/**
 * Cart Activity
 */
class CartActivity : ShopEnableActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)
        title = getString(R.string.cart)

        // Start fragment manager
        supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, CartFragment())
                .commit()

        /**
         * Save order on button click
         */
        cart_button.setOnClickListener {
            // Update data and save cart
            var userId = loginModel.getUserFromPreferences().id
            App.cart.user = userId
            orderModel.saveOrder(App.cart)

            // Cean after insert on database
            App.cart = Order()

            // Go to orders list
            var intent = Intent(applicationContext, OrdersActivity::class.java)
            ContextCompat.startActivity(applicationContext, intent, null)
            finish()
        }
    }
}