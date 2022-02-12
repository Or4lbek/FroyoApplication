package com.sapar.froyoapplication.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sapar.froyoapplication.R
import com.sapar.froyoapplication.model.MyOrdersItem

class MyOrdersAdapter(
    private val mContext: Context,
    private val mOnItemNoteListener: OnItemNoteListener
) : RecyclerView.Adapter<MyOrdersAdapter.MyViewHolder?>() {

    var items: List<MyOrdersItem> = ArrayList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val view: View
        val layoutInflater: LayoutInflater = LayoutInflater.from(mContext)
        view = layoutInflater.inflate(R.layout.my_orders_item, parent, false)
        return MyViewHolder(view, mOnItemNoteListener)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.name.text = items[position].order_name
        holder.status.text = items[position].order_status
        holder.date.text = items[position].order_date
        holder.price.text = items[position].order_price

//        holder.itemView.startAnimation(
//            AnimationUtils.loadAnimation(
//                mContext,
//                R.anim.layout_animation
//            )
//        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class MyViewHolder(itemView: View, private var noteListener: OnItemNoteListener) :
        RecyclerView.ViewHolder(itemView), View.OnClickListener {

        var name: TextView = itemView.findViewById(R.id.textViewMyOrdersName)
        var status: TextView = itemView.findViewById(R.id.textViewEstablishmentStatus)
        var date: TextView = itemView.findViewById(R.id.textViewMyOrdersDate)
        var price: TextView = itemView.findViewById(R.id.textViewMyOrdersPrice)

        override fun onClick(v: View) {
            noteListener.onNoteClick(adapterPosition)
        }

        init {
            itemView.setOnClickListener(this)
        }
    }

    interface OnItemNoteListener {
        fun onNoteClick(position: Int)
    }
}