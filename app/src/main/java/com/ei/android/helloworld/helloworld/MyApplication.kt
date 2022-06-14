package com.ei.android.helloworld.helloworld

import android.app.Application

class MyApplication: Application() {
    lateinit var viewModel: ViewModel

    override fun onCreate() {
        super.onCreate()
        viewModel = ViewModel(Model(CacheDataSource(this)))
    }
}