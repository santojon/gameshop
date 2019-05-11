package com.santojon.shop.ui.main.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.santojon.shop.R
import com.santojon.shop.adapter.ProductAdapter
import com.santojon.shop.base.App
import com.santojon.shop.base.BaseFragment
import com.santojon.shop.ui.main.util.MainView
import kotlinx.android.synthetic.main.fragment_cart.*

/**
 * Cart View Fragment
 */
class CartFragment : BaseFragment(), MainView {
    private val adapter = ProductAdapter()
    private var pageMax = 6

    /**
     * Set providers on creation
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_cart, container, false)

    /**
     * To do after view creation
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerCart.adapter = adapter
        val layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        recyclerCart.layoutManager = layoutManager

        // Update view data
        updateViewData()
    }

    /**
     * Update view data from Database or API
     */
    fun updateViewData() {
        showProgressBar()
        adapter.submitList(App.cart.products)
        hideProgressBar()
    }


    override fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        progressBar.visibility = View.GONE
    }
}