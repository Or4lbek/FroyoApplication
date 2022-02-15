package com.sapar.froyoapplication.ui.my_orders.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sapar.froyoapplication.data.my_orders.MyOrdersItem
import com.sapar.froyoapplication.databinding.ItemMyOrdersBinding

class MyOrdersAdapter(
    val clickListener: (item: MyOrdersItem) -> Unit
) : RecyclerView.Adapter<MyOrdersViewHolder>() {

    var items: List<MyOrdersItem> = ArrayList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyOrdersViewHolder {
        val binding: ItemMyOrdersBinding = ItemMyOrdersBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MyOrdersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyOrdersViewHolder, position: Int) {
        val item: MyOrdersItem = items[position]
        holder.itemView.setOnClickListener { clickListener(item) }
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}