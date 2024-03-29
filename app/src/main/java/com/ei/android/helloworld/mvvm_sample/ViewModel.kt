package com.ei.android.helloworld.mvvm_sample

class ViewModel (private val model: Model){
    private var textObservable: TextObservable? = null

    private val textCallback = object : TextCallback {
        override fun updateText(str: String) {
            textObservable?.postValue(str)
        }
    }
    fun init(textObservable: TextObservable){
        this.textObservable = textObservable
    }
    fun clear(){
        textObservable = null
    }

    fun resumeCounting(){
        model.start(textCallback)
    }

    fun pauseCounting(){
        model.stop()
    }

}

interface TextCallback {
    fun updateText(str: String)
}

class TextObservable {
    private lateinit var callback: TextCallback
    fun observe(callback: TextCallback){
        this.callback = callback
    }

    fun postValue(text: String){
        callback.updateText(text)
    }


}
