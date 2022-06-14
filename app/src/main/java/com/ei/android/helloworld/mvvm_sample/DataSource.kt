package com.ei.android.helloworld.mvvm_sample

interface DataSource {

    fun saveInt(key: String, value: Int)

    fun getInt(key:String):Int
}