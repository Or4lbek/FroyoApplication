package com.sapar.froyoapplication.ui.establishment_detail.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sapar.froyoapplication.data.menu.Category
import com.sapar.froyoapplication.databinding.ItemCateogryBinding

class CategoryAdapter(private val onClickCategory: (Int) -> Unit) :
    RecyclerView.Adapter<CategoryViewHolder>() {

    var items: List<Category> = ArrayList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    private var selectedItemPos = -1
    private var lastItemSelectedPos = -1


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding =
            ItemCateogryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: CategoryViewHolder,
        @SuppressLint("RecyclerView") position: Int
    ) {

        val item = items[position]
        holder.itemView.setOnClickListener { onClickCategory(position) }

        if (item.isCurrent) {
            holder.selectedCardStroke()
            lastItemSelectedPos = position
        } else
            holder.defaultCardStroke()
        holder.bind(item)
    }

    override fun onBindViewHolder(
        holder: CategoryViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isNotEmpty()) {
            when (payloads[0]) {
                2 -> {
                    items[position].isCurrent = false
                }
            }
        }
        super.onBindViewHolder(holder, position, payloads)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun selectItem(pos: Int) {
        if (selectedItemPos != -1)
            items[selectedItemPos].isCurrent = false
        items[pos].isCurrent = true
        selectedItemPos = pos
        notifyDataSetChanged()
    }
}