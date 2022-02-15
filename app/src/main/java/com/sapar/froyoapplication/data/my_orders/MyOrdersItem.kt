package com.sapar.froyoapplication.data.my_orders

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MyOrdersItem(
    val id: Int,
    val order_address: String,
    val order_date: String,
    val order_name: String,
    val order_number: String,
    val order_price: String,
    val order_status: String
) : Parcelable
