package com.example.classtask.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product (
    val id:Int,
    val name:String,
    val description:String):Parcelable{

    }