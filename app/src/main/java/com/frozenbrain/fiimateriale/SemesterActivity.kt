package com.frozenbrain.fiimateriale

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_semester.*

class SemesterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_semester)

        toolbar_semester.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp)
        toolbar_semester.setNavigationOnClickListener{
            finish() // TODO FInd a wae to get the item id
        }

        val semester = intent.getParcelableExtra("semester") as Semester
        semester_title.text = semester.name


    }



}
