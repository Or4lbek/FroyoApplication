package com.sapar.froyoapplication.data.establishment

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class EstablishmentsItem(
    val address: String,
    val id: Int,
    @SerializedName("image_url")
    val imageUrl: String?,
    val name: String
) : Parcelable