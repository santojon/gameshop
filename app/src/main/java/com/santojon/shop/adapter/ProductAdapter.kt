package com.santojon.shop.adapter

import android.content.Intent
import android.support.v4.content.ContextCompat
import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.santojon.shop.R
import com.santojon.shop.room.entity.Product
import com.santojon.shop.ui.main.activity.DetailsActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_product.view.*


/**
 * Adapter used to bind {@link Product} to View
 */
class ProductAdapter : ListAdapter<Product, ProductAdapter.ProductViewHolder>(ProductItemCallback()) {
    private class ProductItemCallback : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product?, newItem: Product?): Boolean = oldItem?.id == newItem?.id
        override fun areContentsTheSame(oldItem: Product?, newItem: Product?): Boolean = oldItem?.name == newItem?.name
    }

    fun isLastItemVisible(): Boolean {
        getItem(itemCount - 1)
        return false
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = getItem(position)
        if (product != null) {
            holder.bind(product)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder =
            ProductViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false))

    /**
     * View Holder to bind data to
     */
    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(product: Product) = with(itemView) {
            product_title.text = product.name
            product_price.text = product.price.toString()
            if (product.image != null) {
                Picasso.get().load(product.image).into(product_image)
            }

            // When an item is clicked
            this.setOnClickListener {
                var intent = Intent(context.applicationContext, DetailsActivity::class.java)
                intent.putExtra("product_id", product.id)
                ContextCompat.startActivity(context.applicationContext, intent, null)
            }
        }
    }
}