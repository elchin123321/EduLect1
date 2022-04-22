package com.ei.android.helloworld

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val agreementTextView: TextView = findViewById(R.id.agreementTextView)
        val fullText = getString(R.string.agreement_full_text)
        val confidential  = getString(R.string.confidential_info)
        val policy = getString(R.string.privacy_policy)
        var spannableString = SpannableString(fullText)

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



    }

}