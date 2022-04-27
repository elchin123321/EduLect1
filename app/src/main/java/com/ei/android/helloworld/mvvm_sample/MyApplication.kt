package com.ei.android.helloworld.mvvm_sample

import android.app.Application

class MyApplication: Application() {
    lateinit var viewModel: ViewModel

    override fun onCreate() {
        super.onCreate()
        viewModel = ViewModel(Model())
    }
}