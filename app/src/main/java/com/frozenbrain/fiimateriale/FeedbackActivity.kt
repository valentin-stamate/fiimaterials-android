package com.frozenbrain.fiimateriale

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_feedback.*

class FeedbackActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feedback)
        toolbar_feedback.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp)
        toolbar_feedback.setNavigationOnClickListener{
            finish()
        }
    }
}
