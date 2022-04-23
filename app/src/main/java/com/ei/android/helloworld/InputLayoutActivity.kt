package com.ei.android.helloworld

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class InputLayoutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_layout)

        val textInputLayout = findViewById<TextInputLayout>(R.id.textInputLayout)
        val textInputEditText = textInputLayout.editText as TextInputEditText

        textInputEditText.listenChanges { text-> Log.d(TAG,text) }
    }

}
fun TextInputEditText.listenChanges(block: (text: String)->Unit){
    addTextChangedListener(object : SimpleTextWatcher(){

        override fun afterTextChanged(p0: Editable?) {
            block.invoke(p0.toString())
        }

    })
}