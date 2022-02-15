package com.sapar.froyoapplication.ui.establishment_detail.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView
import com.sapar.froyoapplication.R
import com.sapar.froyoapplication.data.menu.Meal
import com.sapar.froyoapplication.databinding.ItemMenuBinding
import com.squareup.picasso.Picasso

class ChildMenuAdapter(private val listener: ChildMenuAdapterListener) :
    RecyclerView.Adapter<ChildMenuAdapter.CategoryContainerViewHolder>() {

    var items: List<Meal> = ArrayList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class CategoryContainerViewHolder(private val binding: ItemMenuBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.minusBtn.setOnClickListener {
                if (binding.counts.text.toString().toInt() > 0) {
                    binding.counts.text = binding.counts.text.toString().toInt().minus(1).toString()
                }
            }
            binding.plusBtn.setOnClickListener {
                binding.counts.text = binding.counts.text.toString().toInt().plus(1).toString()
            }
            binding.counts.addTextChangedListener {
                items[absoluteAdapterPosition].counter = binding.counts.text.toString().toInt()
                listener.onChangeCount(
                    items[absoluteAdapterPosition]
                )
            }
        }

        fun bind(item: Meal) {
            binding.counts.text = item.counter.toString()
            binding.name.text = item.name
            binding.price.text = item.getPriceAsString()
            Picasso.get().load(item.imageUrl).placeholder(R.drawable.back)
                .error(R.drawable.back).into(binding.imageViewMenuMealImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryContainerViewHolder {
        val binding =
            ItemMenuBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryContainerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryContainerViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    interface ChildMenuAdapterListener {
        fun onChangeCount(meal: Meal)
    }

}