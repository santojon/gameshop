package com.santojon.shop.ui.main.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.santojon.shop.R
import com.santojon.shop.adapter.ProductAdapter
import com.santojon.shop.base.BaseFragment
import com.santojon.shop.ui.main.util.MainView
import com.santojon.shop.ui.main.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_main.*

/**
 * Main View Fragment
 */
class MainFragment : BaseFragment(), MainView {
    private lateinit var model: MainViewModel
    private val adapter = ProductAdapter()
    private var pageMax = 6
    private var lastPosition = 0

    /**
     * Set providers on creation
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model = ViewModelProviders.of(this).get(MainViewModel::class.java).init(kodein)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_main, container, false)

    /**
     * To do after view creation
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerProduct.adapter = adapter
        val layoutManager = GridLayoutManager(activity, 2)
        recyclerProduct.layoutManager = layoutManager
        recyclerProduct.addOnScrollListener(object : RecyclerView.OnScrollListener() {
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
     * Update view data from Database or API
     */
    fun updateViewData() {
        showProgressBar()
        if (model.isFirstLoad()) {
            model.getProductList(lastPosition + pageMax).observe(this, Observer { list ->
                adapter.submitList(list)
                hideProgressBar()
            })

            model.fetchProductList()
            model.setFirstLoad(false)
        } else {
            model.getProductList(lastPosition + pageMax).observe(this, Observer { list ->
                adapter.submitList(list)
                hideProgressBar()
            })
        }
    }

    override fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        progressBar.visibility = View.GONE
    }
}