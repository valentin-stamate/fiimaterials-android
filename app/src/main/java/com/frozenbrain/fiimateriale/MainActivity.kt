package com.frozenbrain.fiimateriale

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.frozenbrain.fiimateriale.recyclerview.RecyclerViewAdapter
import com.frozenbrain.fiimateriale.recyclerview.model.IntroductionItem
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val introductionTitle = resources.getStringArray(R.array.introduction_list_title)
        val introductionBody = resources.getStringArray(R.array.introduction_list_body)

        val introductionList: MutableList<IntroductionItem> = mutableListOf()

        for (i in 0..2) {
            introductionList.add(IntroductionItem(introductionTitle[i], introductionBody[i]))
        }

        introduction_list.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = RecyclerViewAdapter(introductionList)
        }

    }
}