package com.santojon.shop.adapter

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.santojon.shop.R
import com.santojon.shop.room.entity.Order
import kotlinx.android.synthetic.main.item_order.view.*


/**
 * Adapter used to bind {@link Order} to View
 */
class OrderAdapter : ListAdapter<Order, OrderAdapter.OrderViewHolder>(OrderItemCallback()) {
    private class OrderItemCallback : DiffUtil.ItemCallback<Order>() {
        override fun areItemsTheSame(oldItem: Order?, newItem: Order?): Boolean = oldItem?.id == newItem?.id
        override fun areContentsTheSame(oldItem: Order?, newItem: Order?): Boolean = oldItem?.date == newItem?.date
    }

    fun isLastItemVisible(): Boolean {
        getItem(itemCount - 1)
        return false
    }

    override fun onBindViewHolder(holder: OrderAdapter.OrderViewHolder, position: Int) {
        val order = getItem(position)
        if (order != null) {
            holder.bind(order)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderAdapter.OrderViewHolder =
            OrderAdapter.OrderViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_order, parent, false))

    /**
     * View Holder to bind data to
     */
    class OrderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(order: Order) = with(itemView) {
            order_number.text = order.id.toString()
            order_price.text = order.products.map {
                it.price
            }.fold(0.0F, { a, b -> a + b}).toString()
            order_quantity.text = order.products.size.toString() + " Items"
        }
    }
}