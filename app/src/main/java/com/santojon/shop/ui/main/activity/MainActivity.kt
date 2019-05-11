package com.santojon.shop.ui.main.activity

import android.Manifest
import android.app.Activity
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.view.Menu
import android.view.MenuItem
import com.santojon.shop.R
import com.santojon.shop.base.BaseActivity
import com.santojon.shop.base.ShopEnableActivity
import com.santojon.shop.ui.main.fragment.MainFragment
import com.santojon.shop.ui.main.fragment.OrdersFragment
import com.santojon.shop.ui.main.viewmodel.LoginViewModel


/**
 * Main App Activity
 */
class MainActivity : ShopEnableActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = getString(R.string.app_name)

        // Require Internet permission
        ActivityCompat.requestPermissions(this as Activity, arrayOf(Manifest.permission.INTERNET), 1)

        // Start fragment manager
        supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, MainFragment())
                .commit()
    }
}