package com.example.classtask.features.productList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel: ViewModel() {

    var addProductObserver=MutableLiveData<Boolean>()


    fun onAddNewProduct(){
        addProductObserver.postValue(true)
    }
}