package com.frozenbrain.fiimateriale

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.frozenbrain.fiimateriale.data.AboutDataItem
import com.frozenbrain.fiimateriale.viewmodel.AppViewModel
import kotlinx.android.synthetic.main.activity_about.*

class AboutActivity : AppCompatActivity() {

    private lateinit var viewModel: AppViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)


        Glide.with(this).load(R.drawable.ic_about_image).into(about_header_image)

        viewModel = ViewModelProvider(this).get(AppViewModel::class.java)
        viewModel.onAboutDataListInit()
        val observer =  Observer<MutableList<AboutDataItem>> {
            onInitData(it)
        }
        viewModel.aboutDataList.observe(this, observer)

    }

    private fun onInitData(list: MutableList<AboutDataItem>) {
        // also, a recyclerview for this
        about_first_title.text = list[0].title
        about_first_body.text = list[0].body
        about_second_title.text = list[1].title
        about_second_body.text = list[1].body
        about_third_title.text = list[2].title
        about_third_body.text = list[2].body

        about_progress_bar.visibility = View.GONE
        about_scrollView.visibility = View.VISIBLE

    }
}
