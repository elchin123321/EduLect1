package com.ei.android.helloworld

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation

class MainActivity : AppCompatActivity() {
    private companion object{
        const val URL = "https://interactive-examples.mdn.mozilla.net/media/cc0-images/grapefruit-slice-332-332.jpg"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val agreementTextView: TextView = findViewById(R.id.agreementTextView)
        val fullText = getString(R.string.agreement_full_text)
        val confidential  = getString(R.string.confidential_info)
        val policy = getString(R.string.privacy_policy)
        val spannableString = SpannableString(fullText)

        val image = findViewById<ImageView>(R.id.imageView)
        val image2 = findViewById<ImageView>(R.id.imageView2)
        image.setImagePicasso(URL)
        image2.setImageGlide(URL)


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

    fun ImageView.setImagePicasso(url: String){
        Picasso.get().load(url)
            .resize(120,120)
            .transform(CropCircleTransformation())
            .placeholder(android.R.drawable.ic_media_pause)
            .error(android.R.drawable.ic_dialog_alert)

            .into(this)
    }
    fun ImageView.setImageGlide(url: String){
        Glide.with(this)
            .load(url)
            .apply(RequestOptions().override(120,120))
            .placeholder(android.R.drawable.ic_media_pause)
            .error(android.R.drawable.ic_dialog_alert)
            .circleCrop()
            .into(this)
    }

}