package com.ei.android.helloworld.helloworld

import android.graphics.Bitmap

interface ImageCallback {
    fun success(bitmap: Bitmap)
    fun failed()
}