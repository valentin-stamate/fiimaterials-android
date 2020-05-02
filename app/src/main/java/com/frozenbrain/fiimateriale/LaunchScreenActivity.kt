package com.frozenbrain.fiimateriale

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.frozenbrain.fiimateriale.viewmodel.AppViewModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_launch_screen.*

class LaunchScreenActivity : AppCompatActivity() {

    private lateinit var db: DatabaseReference
    private lateinit var viewModel: AppViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch_screen)
        Glide.with(this).load(R.drawable.ic_launch_screen_bg).into(launchScreenBackground)

        db = FirebaseDatabase.getInstance().reference
        // TODO prompt for internet connection
        viewModel = ViewModelProvider(this).get(AppViewModel::class.java)

    }
}
