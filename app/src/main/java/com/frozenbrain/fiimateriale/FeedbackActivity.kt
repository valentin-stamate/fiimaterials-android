package com.frozenbrain.fiimateriale

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.frozenbrain.fiimateriale.data.Feedback
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_feedback.*

class FeedbackActivity : AppCompatActivity() {

    lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feedback)
        toolbar_feedback.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp)
        toolbar_feedback.setNavigationOnClickListener{
            finish()
        }

        db = FirebaseFirestore.getInstance()

        feedback_sent.setOnClickListener {
            sentFeedback()
        }

    }

    private fun sentFeedback() {

        var name = feedback_nameField.editText?.text.toString()
        val subject = feedback_subjectField.editText?.text.toString()
        val message = feedback_messageField.editText?.text.toString()

        if (name.length < 3) name = "Unknown"

        feedback_nameField.editText?.setText("")
        feedback_nameField.editText?.clearFocus()
        feedback_subjectField.editText?.setText("")
        feedback_subjectField.editText?.clearFocus()
        feedback_messageField.editText?.setText("")
        feedback_messageField.editText?.clearFocus()

        val contextView: View = findViewById(R.id.feedback_container)
        if (subject.length > 3 && message.length > 10) {
            val fb = Feedback(name, subject, message, false)

            db.collection("Feedback").add(fb).addOnSuccessListener {
                Snackbar.make(contextView, "Feedback sent", Snackbar.LENGTH_LONG).show()
            }.addOnFailureListener {
                Snackbar.make(contextView, "Failure", Snackbar.LENGTH_LONG).show()
            }

        } else {
            Snackbar.make(contextView, "Well, show me a real feedback", Snackbar.LENGTH_LONG).show()
        }
        closeKeyboard()
    }

    private fun closeKeyboard() {
        val view: View? = currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        }
    }

    // https://gist.github.com/Audhil/3e4332e14f0583062ead8147ab185d7b
    fun generateRandomPassword(): String {
        val chars = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
        var passWord = ""
        for (i in 0..31) {
            passWord += chars[Math.floor(Math.random() * chars.length).toInt()]
        }
        return passWord
    }

}
