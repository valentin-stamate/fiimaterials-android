package com.frozenbrain.fiimateriale

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.miguelcatalan.materialsearchview.MaterialSearchView
import com.miguelcatalan.materialsearchview.MaterialSearchView.SearchViewListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*


class MainActivity : AppCompatActivity() {

    companion object  {
        lateinit var searchView: MaterialSearchView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setIcon(R.drawable.ic_logo)


        val semester = Semester("First Year T", "ldjasldsa.com")

        yearOneLeft.setOnClickListener {
            val intent = Intent(this, SemesterActivity::class.java)
            intent.putExtra("semester", semester)
            startActivity(intent)
        }


    }
}
