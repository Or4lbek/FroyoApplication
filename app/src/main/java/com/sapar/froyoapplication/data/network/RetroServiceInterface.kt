package com.sapar.froyoapplication.data.network

import com.sapar.froyoapplication.data.establishment.EstablishmentsItem
import com.sapar.froyoapplication.data.my_orders.MyOrdersItem
import retrofit2.http.GET

interface RetroServiceInterface {

    @GET("8910434b-700b-4e77-8eef-34a52a533ecf")
    suspend fun getEstablishments(): List<EstablishmentsItem>

    @GET("95c7c38b-0ded-4acc-ae02-a1470415b145")
    suspend fun getMyOrders(): List<MyOrdersItem>
}