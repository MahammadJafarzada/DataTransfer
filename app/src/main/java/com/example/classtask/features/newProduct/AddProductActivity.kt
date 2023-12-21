package com.example.classtask.features.newProduct

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.classtask.databinding.ActivityAddProductBinding
import com.example.classtask.databinding.ActivityMainBinding
import com.example.classtask.features.productList.MainActivityViewModel
import com.example.classtask.model.Product

class AddProductActivity: AppCompatActivity() {
    lateinit var binding: ActivityAddProductBinding
    lateinit var viewModel: AddProductViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AddProductViewModel::class.java)

        binding = ActivityAddProductBinding.inflate(layoutInflater)

        setContentView(binding.root)
        binding.viewModel=viewModel
        binding.lifecycleOwner=this
    }

    override fun onResume() {
        super.onResume()
        observerAll()
    }
    fun observerAll(){
        viewModel.newProductCallback.observe(this){
            val intent = Intent()
            val product= Product(viewModel.code.value.orEmpty(),viewModel.name.value.orEmpty(),viewModel.description.value.orEmpty())
            intent.putExtra("product",product)
            setResult(RESULT_OK, intent)
            finish()
        }
        viewModel.errorDescription.observe(this){
            if(!it.isNullOrEmpty()){
                Toast.makeText(this,it, Toast.LENGTH_SHORT).show()
            }
        }
    }
}