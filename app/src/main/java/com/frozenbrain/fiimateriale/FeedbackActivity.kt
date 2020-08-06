package com.frozenbrain.fiimateriale

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.frozenbrain.fiimateriale.data.Data
import com.frozenbrain.fiimateriale.data.Feedback
import com.frozenbrain.fiimateriale.data.OnItemClickListener
import com.frozenbrain.fiimateriale.recycler_view.RecyclerViewAdapter
import com.frozenbrain.fiimateriale.viewmodel.AppViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_feedback.*

class FeedbackActivity : AppCompatActivity(), OnItemClickListener {

    private lateinit var db: FirebaseFirestore
    private lateinit var viewModel: AppViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feedback)
        toolbar_feedback.setNavigationIcon(R.drawable.ic_arrow_back)
        toolbar_feedback.setNavigationOnClickListener{
            finish()
        }

        db = FirebaseFirestore.getInstance()
        viewModel = ViewModelProvider(this).get(AppViewModel::class.java)
        viewModel.onFeedbackListInit()

        val listObserver = Observer<MutableList<Data>> {
            feedbackRecyclerView.apply {
                val lm = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, true)
                lm.stackFromEnd = true
                layoutManager = lm
                adapter = RecyclerViewAdapter(it, this@FeedbackActivity)
            }
            // feedbackRecyclerView.scrollToPosition(it.size) TODO fix this
        }
        viewModel.feedbackList.observe(this, listObserver)

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
            val fb = Feedback(name, subject, message)

            db.collection("Feedback").add(fb).addOnSuccessListener {
                Snackbar.make(contextView, "Thanks For Feedback!", Snackbar.LENGTH_LONG).show()
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
}
