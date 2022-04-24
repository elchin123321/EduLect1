package com.ei.android.helloworld

import android.content.ContentValues.TAG
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.util.Log
import android.util.Patterns.EMAIL_ADDRESS
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.CheckBox
import android.widget.ProgressBar
import android.widget.TextView
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
        val checkBox = findViewById<CheckBox>(R.id.checkBox)
        val spannableString = SpannableString(getString(R.string.agreement_full_text))
        val agreementTextView = findViewById<TextView>(R.id.agreementCBTextView)
        val fullText = getString(R.string.agreement_full_text)
        val confidential  = getString(R.string.confidential_info)
        val policy = getString(R.string.privacy_policy)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        val contentLayout = findViewById<View>(R.id.content_layout)
        val confidetialClicable =MyClickableSpan{
            Snackbar.make(it,"GoToL1",Snackbar.LENGTH_SHORT).show()

        }

        val policyClicable = MyClickableSpan{
            Snackbar.make(it,"GoToL2",Snackbar.LENGTH_SHORT).show()
        }

        spannableString.setSpan(
            confidetialClicable,
            fullText.indexOf(confidential),
            fullText.indexOf(confidential)+confidential.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        spannableString.setSpan(
            policyClicable,
            fullText.indexOf(policy),
            fullText.indexOf(policy)+policy.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        agreementTextView.run {
            text = spannableString
            movementMethod = LinkMovementMethod.getInstance()
            highlightColor = Color.TRANSPARENT
        }
        loginButton.isEnabled = false
        checkBox.setOnCheckedChangeListener{_,isChecked->loginButton.isEnabled = isChecked}

        loginButton.setOnClickListener{
            if(EMAIL_ADDRESS.matcher(textInputEditText.text.toString()).matches()){
                hideKeyboard(textInputEditText)
                contentLayout.visibility = View.GONE
                progressBar.visibility = View.VISIBLE
                Snackbar.make(loginButton,"Go to postLogin",Snackbar.LENGTH_SHORT).show()
                Handler(Looper.myLooper()!!).postDelayed({
                    contentLayout.visibility = View.VISIBLE
                    progressBar.visibility = View.GONE
                }, 3000)
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