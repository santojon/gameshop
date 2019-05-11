package com.santojon.shop.ui.main.activity

import android.os.Bundle
import com.santojon.shop.R
import com.santojon.shop.base.ShopEnableActivity
import com.santojon.shop.ui.main.fragment.OrdersFragment


/**
 * Orders Activity
 */
class OrdersActivity : ShopEnableActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = getString(R.string.orders)

        // Start fragment manager
        supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, OrdersFragment())
                .commit()
    }
}