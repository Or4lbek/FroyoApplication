package com.sapar.froyoapplication.ui.establishment_detail.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sapar.froyoapplication.databinding.ItemParentMenuBinding
import com.sapar.froyoapplication.model.menu.CategoryMeal

class ParentMenuAdapter :
    RecyclerView.Adapter<ParentMenuAdapter.CategoryContainerViewHolder>() {

    var items: List<CategoryMeal> = ArrayList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class CategoryContainerViewHolder(private val binding: ItemParentMenuBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CategoryMeal) {
            binding.category.text = item.category
            binding.mealRv.adapter = ChildMenuAdapter().apply {
                items = item.meals
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryContainerViewHolder {
        val binding =
            ItemParentMenuBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryContainerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryContainerViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}