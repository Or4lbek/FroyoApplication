package com.sapar.froyoapplication.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class EstablishmentsItem(
    val address: String,
    val id: Int,
    @SerializedName("image_url")
    val imageUrl: String?,
    val name: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readString().toString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(address)
        parcel.writeInt(id)
        parcel.writeString(imageUrl)
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<EstablishmentsItem> {
        override fun createFromParcel(parcel: Parcel): EstablishmentsItem {
            return EstablishmentsItem(parcel)
        }

        override fun newArray(size: Int): Array<EstablishmentsItem?> {
            return arrayOfNulls(size)
        }
    }
}