package com.sapar.froyoapplication.ui.establishment_detail.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sapar.froyoapplication.databinding.ItemCateogryBinding
import com.sapar.froyoapplication.model.menu.Category


class CategoryAdapter :
    RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    var items: List<Category> = ArrayList()
    @SuppressLint("NotifyDataSetChanged")
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    var selectedItemPos = -1
    var lastItemSelectedPos = -1

    inner class CategoryViewHolder(private val binding: ItemCateogryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(category: Category) {
            binding.name.text = category.name
        }
//        private fun changeSelectedPosition() {
//            selectedItemPos = absoluteAdapterPosition
//            when {
//                lastItemSelectedPos != -1 && lastItemSelectedPos != selectedItemPos -> {
//                    notifyItemChanged(lastItemSelectedPos, 2)
//                }
//            }
//            notifyItemChanged(selectedItemPos, 0)
//        }

        fun defaultCardStroke(ownBinding: ItemCateogryBinding = binding) {
            ownBinding.name.setTextColor(Color.BLUE)
            ownBinding.root.strokeColor = Color.GREEN
        }

        fun selectedCardStroke() {
            binding.name.setTextColor(Color.DKGRAY)
            binding.root.strokeColor = Color.MAGENTA
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding =
            ItemCateogryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val item = items[position]
        if (item.isCurrent) {
            holder.selectedCardStroke()
            lastItemSelectedPos = position
        } else
            holder.defaultCardStroke()
        holder.bind(item)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int, payloads: MutableList<Any>) {
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
}