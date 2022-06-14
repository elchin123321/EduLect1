package com.ei.android.helloworld.helloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.ei.android.helloworld.R

class SampleActivity : AppCompatActivity() {

    private lateinit var viewModel: ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample)
        viewModel = (application as MyApplication).viewModel
        val textView = findViewById<TextView>(R.id.textView)
        val observable = TextObservable()
        observable.observe(object : TextCallback {
            override fun updateText(str: String) = runOnUiThread(){
                textView.text = str
            }
        })
        viewModel.init(observable)
    }

    override fun onDestroy() {
        viewModel.clear()
        super.onDestroy()
    }

    override fun onResume() {
        super.onResume()
        viewModel.resumeCounting()
    }

    override fun onPause() {
        super.onPause()
        viewModel.pauseCounting()
    }
}