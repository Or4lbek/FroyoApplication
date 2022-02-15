package com.sapar.froyoapplication.ui.establishment_detail.adapter

import android.graphics.Color
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.sapar.froyoapplication.R
import com.sapar.froyoapplication.data.menu.Category
import com.sapar.froyoapplication.databinding.ItemCateogryBinding

class CategoryViewHolder(private val binding: ItemCateogryBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(category: Category) {
        binding.name.text = category.name
    }

    fun defaultCardStroke(ownBinding: ItemCateogryBinding = binding) {
        ownBinding.name.setTextColor(
            ContextCompat.getColor(
                binding.root.context,
                R.color.graySecond
            )
        )
        ownBinding.root.setCardBackgroundColor(Color.WHITE)
    }

    fun selectedCardStroke() {
        binding.root.setCardBackgroundColor(
            ContextCompat.getColor(
                binding.root.context,
                R.color.gray
            )
        )
    }
}