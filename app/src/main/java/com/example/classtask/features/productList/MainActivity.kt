package com.example.classtask.features.productList

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import com.example.classtask.R
import com.example.classtask.databinding.ActivityMainBinding
import com.example.classtask.features.newProduct.AddProductActivity
import com.example.classtask.model.Product

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel= ViewModelProvider(this).get(MainActivityViewModel::class.java)

        binding= ActivityMainBinding.inflate(layoutInflater)
        binding.lifecycleOwner=this
        binding.viewModel=viewModel
        setContentView(binding.root)
    }

    fun openAddProductActivity(){
        val intent = Intent(this,AddProductActivity::class.java)
        startActivity(intent)
    }

    private val newProductLauncher=registerForActivityResult(ActivityResultContracts.StartActivityForResult()){result->
        if(result.resultCode== Activity.RESULT_OK){
            val item=result.data?.getParcelableExtra<Product>("product" )
        }
    }

    fun observeAll(){
        viewModel.addProductObserver.observe(this){
            if(it){
                openAddProductActivity()
            }
        }
    }

    fun removeObservers(){
        viewModel.addProductObserver.removeObservers(this)
       // viewModel.addProductObserver.postValue(true)
    }


    override fun onResume(){
        super.onResume()
        observeAll()
    }
    override fun onPause(){
        super.onPause()
        removeObservers()
    }
}