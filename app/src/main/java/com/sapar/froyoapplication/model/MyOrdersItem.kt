package com.sapar.froyoapplication.model

import android.os.Parcel
import android.os.Parcelable
import kotlinx.serialization.Serializable

@Serializable
data class MyOrdersItem(
    val id: Int,
    val order_address: String,
    val order_date: String,
    val order_name: String,
    val order_number: String,
    val order_price: String,
    val order_status: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString()
    )
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(order_address)
        parcel.writeString(order_date)
        parcel.writeString(order_name)
        parcel.writeString(order_number)
        parcel.writeString(order_price)
        parcel.writeString(order_status)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MyOrdersItem> {
        override fun createFromParcel(parcel: Parcel): MyOrdersItem {
            return MyOrdersItem(parcel)
        }

        override fun newArray(size: Int): Array<MyOrdersItem?> {
            return arrayOfNulls(size)
        }
    }

}