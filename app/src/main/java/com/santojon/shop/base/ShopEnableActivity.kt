package com.santojon.shop.base

import android.annotation.SuppressLint
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.santojon.shop.R
import com.santojon.shop.room.entity.Product
import com.santojon.shop.ui.main.activity.CartActivity
import com.santojon.shop.ui.main.activity.LoginActivity
import com.santojon.shop.ui.main.activity.OrdersActivity
import com.santojon.shop.ui.main.viewmodel.OrderViewModel

/**
 * Base Activity for all needed ones
 */
@SuppressLint("Registered")
abstract class ShopEnableActivity : BaseActivity() {
    protected lateinit var orderModel: OrderViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ViewModel provider register
        orderModel = ViewModelProviders.of(this).get(OrderViewModel::class.java).init(kodein)
    }

    fun addToCart(product: Product) {
        App.cart.products.add(product)
        Toast.makeText(applicationContext, resources.getText(R.string.added_to_cart), Toast.LENGTH_LONG).show()
    }

    /**
     * When menu is created
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.user_menu, menu)
        return true
    }

    /**
     * Toolbar options menu actions
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.orders -> {
                var intent = Intent(applicationContext, OrdersActivity::class.java)
                startActivity(intent)
            }

            // Logout action
            R.id.logout -> {
                var intent = Intent(applicationContext, LoginActivity::class.java)

                // Reset User in sharedPreferences
                loginModel.setUserEmail("")
                loginModel.setUserPassword("")

                startActivity(intent)
            }

            R.id.cart -> {
                var intent = Intent(applicationContext, CartActivity::class.java)
                startActivity(intent)
            }
        }

        return true
    }
}