package com.ei.android.helloworld

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.Patterns.EMAIL_ADDRESS
import android.view.View
import android.view.inputmethod.InputMethodInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class InputLayoutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_layout)

        val textInputLayout = findViewById<TextInputLayout>(R.id.textInputLayout)
        val textInputEditText = textInputLayout.editText as TextInputEditText
        val textInputLayoutPassword = findViewById<TextInputLayout>(R.id.textInputLayoutPassword)
        val textInputEditTextPassword = textInputLayoutPassword.editText as TextInputEditText
        val loginButton = findViewById<Button>(R.id.login_button)
        loginButton.setOnClickListener{
            if(EMAIL_ADDRESS.matcher(textInputEditText.text.toString()).matches()){
                hideKeyboard(textInputEditText)
                loginButton.isEnabled = false
                Snackbar.make(loginButton,"Go to postLogin",Snackbar.LENGTH_SHORT).show()
            }else{
                textInputLayout.isErrorEnabled = true
                textInputLayout.error = getString(R.string.invalid_email_message)
            }
        }

        textInputEditText.listenChanges { text-> Log.d(TAG,text) }
        textInputEditText.listenChanges { textInputLayout.isErrorEnabled = false }
    }

}
fun AppCompatActivity.hideKeyboard(view: View){
    val imm = this.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(view.windowToken,0)
}
fun TextInputEditText.listenChanges(block: (text: String)->Unit){
    addTextChangedListener(object : SimpleTextWatcher(){

        override fun afterTextChanged(p0: Editable?) {
            block.invoke(p0.toString())
        }

    })
}