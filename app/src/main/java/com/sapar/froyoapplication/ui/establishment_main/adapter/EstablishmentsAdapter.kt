package com.sapar.froyoapplication.ui.establishment_main.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sapar.froyoapplication.data.establishment.EstablishmentsItem
import com.sapar.froyoapplication.databinding.ItemEstablishmentRecordBinding

class EstablishmentsAdapter(
    val clickListener: (item: EstablishmentsItem) -> Unit
) : RecyclerView.Adapter<EstablishmentViewHolder>() {

    var items: List<EstablishmentsItem> = ArrayList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EstablishmentViewHolder {
        val binding = ItemEstablishmentRecordBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return EstablishmentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EstablishmentViewHolder, position: Int) {
        val item: EstablishmentsItem = items[position]
        holder.itemView.setOnClickListener { clickListener(item) }
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}