package com.sapar.froyoapplication.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sapar.froyoapplication.R
import com.sapar.froyoapplication.model.EstablishmentsItem
import com.squareup.picasso.Picasso

class EstablishmentsAdapter(
    private val mContext: Context,
    private val mOnItemNoteListener: OnItemNoteListener
) : RecyclerView.Adapter<EstablishmentsAdapter.MyViewHolder?>() {

    var items: List<EstablishmentsItem> = ArrayList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val view: View
        val layoutInflater: LayoutInflater = LayoutInflater.from(mContext)
        view = layoutInflater.inflate(R.layout.establishment_record_item, parent, false)
        return MyViewHolder(view, mOnItemNoteListener)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.name.text = items[position].name
        holder.address.text = items[position].address

        Picasso.get().load(items[position].imageUrl).placeholder(R.drawable.back)
            .error(R.drawable.back).into(holder.image)

        holder.itemView.startAnimation(
            AnimationUtils.loadAnimation(
                mContext,
                R.anim.recycler_view_animation
            )
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class MyViewHolder(itemView: View, private var noteListener: OnItemNoteListener) :
        RecyclerView.ViewHolder(itemView), View.OnClickListener {

        var name: TextView = itemView.findViewById(R.id.textViewMyOrderName)
        var image: ImageView = itemView.findViewById(R.id.imageViewEstablishmentIcon)
        var address: TextView = itemView.findViewById(R.id.textViewMyOrderAddress)

        override fun onClick(v: View) {
            noteListener.onNoteClick(absoluteAdapterPosition)
        }

        init {
            itemView.setOnClickListener(this)
        }
    }

    interface OnItemNoteListener {
        fun onNoteClick(position: Int)
    }
}