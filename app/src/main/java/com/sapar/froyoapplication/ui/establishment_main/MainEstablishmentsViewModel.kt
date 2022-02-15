package com.sapar.froyoapplication.ui.establishment_main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sapar.froyoapplication.data.establishment.EstablishmentsItem
import com.sapar.froyoapplication.data.network.RetroInstance
import com.sapar.froyoapplication.data.network.RetroServiceInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainEstablishmentsViewModel : ViewModel() {

    private val liveDataEstablishmentsMutable: MutableLiveData<List<EstablishmentsItem>> =
        MutableLiveData()
    val liveDataEstablishments: LiveData<List<EstablishmentsItem>> = liveDataEstablishmentsMutable

    fun loadEstablishmentsApi() {
        viewModelScope.launch(Dispatchers.IO) {
            val retroInstance = RetroInstance.getRetrofitInstance()
            val retroService = retroInstance.create(RetroServiceInterface::class.java)
            val call = retroService.getEstablishments()
            liveDataEstablishmentsMutable.postValue(call)
        }
    }
}