package com.frozenbrain.fiimateriale

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.frozenbrain.fiimateriale.repository.Repository
import com.frozenbrain.fiimateriale.viewmodel.AppViewModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_launch_screen.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LaunchScreenActivity : AppCompatActivity() {

    private lateinit var db: DatabaseReference
    private lateinit var dbFirestore: FirebaseFirestore
    private lateinit var viewModel: AppViewModel
    private lateinit var repository: Repository

    private val APP_OK_TO_RUN = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch_screen)
        Glide.with(this).load(R.drawable.ic_launch_screen_bg).into(launchScreenBackground)

        db = FirebaseDatabase.getInstance().reference
        dbFirestore = FirebaseFirestore.getInstance()
        // TODO prompt for internet connection
        viewModel = ViewModelProvider(this).get(AppViewModel::class.java)

        repository = Repository

        if (repository.dataFetch == APP_OK_TO_RUN) {
            onInitApp()
        } else {
            repository.dataFetch = 0
            // i know that could be done without coroutines but, practice is good
            CoroutineScope(IO).launch {
                repository.fetchUsefulLinks(db.child("Useful Links"))
            }

            CoroutineScope(IO).launch {
                repository.fetchAboutDataList(db.child("Strings/About"))
            }
            CoroutineScope(IO).launch {
                repository.fetchHofPersons(db.child("Hall Of Fame"))
            }
            CoroutineScope(IO).launch {
                repository.fetchFeedback(dbFirestore)
            }

            CoroutineScope(Main).launch {
                onCheckFetching()
            }
        }

    }

    // TODO later check internet connection

    private suspend fun onCheckFetching() {
        var rep = 40 // aka 10 seconds
        while (rep >= 0) {
            delay(250)
            if (Repository.dataFetch == APP_OK_TO_RUN) {
                onInitApp()
                return
            }
            rep--
        }
        progressBar.visibility = View.GONE
        Toast.makeText(this, "Failure On Fetching Data", Toast.LENGTH_SHORT).show()
    }

    private fun onInitApp() {
        val mainIntent = Intent(this, MainActivity::class.java)
        startActivity(mainIntent)
        finish()
    }

//    override fun onDestroy() {
//        super.onDestroy()
//        Toast.makeText(this, "LockScreen Destroyed", Toast.LENGTH_LONG).show()
//    }

}
