package com.santojon.shop.ui.main.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.santojon.shop.R
import com.santojon.shop.adapter.OrderAdapter
import com.santojon.shop.base.BaseFragment
import com.santojon.shop.room.entity.Product
import com.santojon.shop.room.entity.User
import com.santojon.shop.ui.main.util.MainView
import com.santojon.shop.ui.main.viewmodel.LoginViewModel
import com.santojon.shop.ui.main.viewmodel.OrderViewModel
import kotlinx.android.synthetic.main.fragment_orders.*

/**
 * Orders View Fragment
 */
class OrdersFragment : BaseFragment(), MainView {
    protected lateinit var loginModel: LoginViewModel
    lateinit var user: User

    private lateinit var model: OrderViewModel
    private val adapter = OrderAdapter()
    private var pageMax = 10
    private var lastPosition = 0

    /**
     * Set providers on creation
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ViewModel providers register
        model = ViewModelProviders.of(this).get(OrderViewModel::class.java).init(kodein)
        loginModel = ViewModelProviders.of(this).get(LoginViewModel::class.java).init(kodein)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_orders, container, false)

    /**
     * To do after view creation
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        user = loginModel.getUserFromPreferences()

        recyclerOrder.adapter = adapter
        val layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        recyclerOrder.layoutManager = layoutManager
        recyclerOrder.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                lastPosition = layoutManager.findLastCompletelyVisibleItemPosition()
                updateViewData()
            }
        })

        // Load updated data from API
        updateViewData()
    }

    /**
     * Update view data from Database
     */
    fun updateViewData() {
        showProgressBar()
        model.getOrderList(user, lastPosition + pageMax).observe(this, Observer { list ->
            var updatetList = list?.map { order ->
                var products: MutableList<Product> = model.getOrderProductList(order, pageMax)
                order.products = products
                order
            }
            adapter.submitList(updatetList)
            hideProgressBar()
        })
    }

    override fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        progressBar.visibility = View.GONE
    }
}