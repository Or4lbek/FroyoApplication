package com.sapar.froyoapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sapar.froyoapplication.model.MyOrdersItem
import com.sapar.froyoapplication.model.network.RetroInstance
import com.sapar.froyoapplication.model.network.RetroServiceInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyOrdersViewModel : ViewModel() {

    private val liveDataMyOrdersMutable: MutableLiveData<List<MyOrdersItem>> =
        MutableLiveData()
    val liveDataMyOrders: LiveData<List<MyOrdersItem>> = liveDataMyOrdersMutable

    fun loadMyOrdersApi() {
        viewModelScope.launch(Dispatchers.IO) {
            val retroInstance = RetroInstance.getRetrofitInstance()
            val retroService = retroInstance.create(RetroServiceInterface::class.java)
            val call = retroService.getMyOrders()
            liveDataMyOrdersMutable.postValue(call)
        }
    }
}