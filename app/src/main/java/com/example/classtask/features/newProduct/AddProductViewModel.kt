package com.example.classtask.features.newProduct

import android.content.MutableContextWrapper
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.classtask.model.Product

class AddProductViewModel:ViewModel() {
    val code=MutableLiveData<String>()
    val name=MutableLiveData<String>()
    val description=MutableLiveData<String>()
    val newProductCallback=MutableLiveData<Boolean>()
    val errorDescription=MutableLiveData<String>()

    fun createNewProduct():Product{
        return  Product(0, code.value?:"",description.value?:"")
    }

     fun onNewProductInserted(){
         if(code.value.isNullOrEmpty()|| name.value.isNullOrEmpty() || description.value.isNullOrEmpty()){
            errorDescription.postValue("Lutfen tum bilgileri doldurun")
            return
         }
         newProductCallback.postValue(true)
     }
}