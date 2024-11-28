package com.example.viacep.presenter.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.viacep.domain.model.Address
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor() : ViewModel() {

    private val _addressList = MutableLiveData<MutableList<Address>>()
    val currentAddressList: LiveData<MutableList<Address>> = _addressList

    fun insertAddress(address: Address) {
        val currentList = _addressList.value ?: mutableListOf()
        currentList.add(address)
        currentList.also { _addressList.value = it }
    }
}