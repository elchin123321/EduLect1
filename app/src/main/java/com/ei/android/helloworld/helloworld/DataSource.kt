package com.ei.android.helloworld.helloworld

interface DataSource {

    fun saveInt(key: String, value: Int)

    fun getInt(key:String):Int
}