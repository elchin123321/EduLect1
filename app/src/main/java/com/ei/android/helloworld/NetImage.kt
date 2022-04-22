package com.ei.android.helloworld

import android.graphics.BitmapFactory
import java.lang.Exception
import java.net.URL

class NetImage(
    private val url: String,
    private val callback: ImageCallback
):Thread()
{
    override fun run(){
        super.run()
        try{
            val connection = URL(url).openConnection()
            connection.doInput = true
            connection.connect()
            connection.inputStream.use {
                callback.success(BitmapFactory.decodeStream(it))
            }

        }catch (e: Exception){
            callback.failed()
        }
    }
}