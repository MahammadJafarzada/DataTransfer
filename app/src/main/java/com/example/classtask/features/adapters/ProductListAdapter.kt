package com.example.classtask.features.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.annotation.ContentView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import com.example.classtask.R
import com.example.classtask.databinding.ListItemProductBinding
import com.example.classtask.model.Product

class ProductListAdapter(var context: Context, private var dataList:MutableList<Product>,var onItemClick:(Product)->Unit) : BaseAdapter() {

    fun addNewItem(aProduct: Product){
        dataList.add(aProduct)
        notifyDataSetChanged()
    }
    override fun getCount(): Int {
        return  dataList.count()
    }

    override fun getItem(position: Int): Any {
        return dataList[position]
    }

    override fun getItemId(position: Int): Long {
       return  position.toLong()
    }

    override fun getView(position: Int, contentView: View?, p2: ViewGroup?): View {

        var newContentView=contentView
        var holder:ViewHolder

        if (contentView == null) {
            val binding: ListItemProductBinding = DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.list_item_product,
                p2,
                false
            )
            newContentView=binding.root
            holder=ViewHolder(binding,onItemClick)
            holder.bind(dataList[position])
            newContentView?.tag=holder
            return binding.root
        }
        else{
            holder=contentView.tag as ViewHolder
            holder.bind(dataList[position])
        }
        return newContentView!!
    }
    private class ViewHolder( var binding: ListItemProductBinding,var onItemClick:(Product)->Unit){
        fun bind(product: Product){
            binding.txtProductName.text=product.name
            binding.txtProductDetail.text=product.description
            binding.product=product

            binding.root.setOnClickListener{
                onItemClick(binding.product as Product)
            }
        }

    }

}