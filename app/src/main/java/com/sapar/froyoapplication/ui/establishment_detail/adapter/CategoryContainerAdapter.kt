package com.sapar.froyoapplication.ui.establishment_detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sapar.froyoapplication.databinding.ItemCategoryContainerBinding

class CategoryContainerAdapter(private val childAdapter: CategoryAdapter) :
    RecyclerView.Adapter<CategoryContainerAdapter.CategoryContainerViewHolder>() {


    inner class CategoryContainerViewHolder(private val binding: ItemCategoryContainerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            binding.containerRv.adapter = childAdapter
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryContainerViewHolder {
        val binding =
            ItemCategoryContainerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryContainerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryContainerViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int {
        return 1
    }
}