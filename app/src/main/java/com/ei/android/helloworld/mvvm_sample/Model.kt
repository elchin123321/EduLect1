package com.ei.android.helloworld.mvvm_sample

import java.util.*

class Model(private val dataSource: DataSource){
    private var timer: Timer? = null
    private val timerTask get() = object : TimerTask(){
        override fun run() {
            count++
            callback?.updateText(count.toString())
        }
    }
    private var callback: TextCallback? = null
    private var count = -1


    fun start(textCallback: TextCallback) {
        callback = textCallback
        if(count < 0)
            count = dataSource.getInt(COUNTER_KEY)
            timer = Timer()
            timer?.scheduleAtFixedRate(timerTask, 0, 1000)

    }
    fun stop(){
        dataSource.saveInt(COUNTER_KEY,count)
        timer?.cancel()
        timer = null
    }
    companion object{
        private const val COUNTER_KEY = "counterKey"
    }
}