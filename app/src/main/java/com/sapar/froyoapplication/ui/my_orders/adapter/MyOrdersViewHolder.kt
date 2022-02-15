package com.sapar.froyoapplication.ui.my_orders.adapter

import androidx.recyclerview.widget.RecyclerView
import com.sapar.froyoapplication.data.my_orders.MyOrdersItem
import com.sapar.froyoapplication.databinding.ItemMyOrdersBinding


class MyOrdersViewHolder(
    val binding: ItemMyOrdersBinding
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: MyOrdersItem) = with(binding) {
        textViewMyOrdersName.text = item.order_name
        textViewMyOrdersStatus.text = item.order_status
        textViewMyOrdersDate.text = item.order_date
        textViewMyOrdersPrice.text = item.order_price
    }
}