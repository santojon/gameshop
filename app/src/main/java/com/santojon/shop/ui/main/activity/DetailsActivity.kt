package com.santojon.shop.ui.main.activity

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.Menu
import com.santojon.shop.R
import com.santojon.shop.base.ShopEnableActivity
import com.santojon.shop.ui.main.viewmodel.ProductDetailsViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_details.*

/**
 * Product Details Activity
 */
class DetailsActivity : ShopEnableActivity() {
    private lateinit var model: ProductDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        // ViewModel provider register
        model = ViewModelProviders.of(this).get(ProductDetailsViewModel::class.java).init(kodein)

        // Get product by id
        var productId = intent.getLongExtra("product_id", 0L)
        var product = model.getProduct(productId)

        title = product.name
        product_title.text = product.name
        product_price.text = product.price.toString()
        product_description.text = product.description
        if (product.image != null) {
            Picasso.get().load(product.image).into(product_image)
        }

        // Add to cart action
        cart_button.setOnClickListener {
            addToCart(product)
        }
    }

    /**
     * When menu is created
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.user_menu, menu)
        return true
    }
}