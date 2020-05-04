package com.frozenbrain.fiimateriale

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.frozenbrain.fiimateriale.data.Data
import com.frozenbrain.fiimateriale.data.HofPerson
import com.frozenbrain.fiimateriale.data.OnItemClickListener
import com.frozenbrain.fiimateriale.recycler_view.RecyclerViewAdapter
import com.frozenbrain.fiimateriale.viewmodel.AppViewModel
import kotlinx.android.synthetic.main.activity_feedback.toolbar_feedback
import kotlinx.android.synthetic.main.activity_hall_of_fame.*

class HallOfFameActivity : AppCompatActivity(), OnItemClickListener {

    private lateinit var viewModel: AppViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hall_of_fame)
        toolbar_feedback.setNavigationIcon(R.drawable.ic_arrow_back)
        toolbar_feedback.setNavigationOnClickListener{
            finish()
        }

        val observable = Observer<MutableList<Data>> {
            onRecyclerViewInit(it)
        }

        viewModel = ViewModelProvider(this).get(AppViewModel::class.java)
        viewModel.onHofPersonListInit()
        viewModel.hofPersonList.observe(this, observable)

    }

    private fun onRecyclerViewInit(list: MutableList<Data>) {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = RecyclerViewAdapter(list, this@HallOfFameActivity)
        }
    }

    override fun onItemClicked(item: Data, type: Int) {
        item as HofPerson
        startActivity( Intent(Intent.ACTION_VIEW, Uri.parse(item.link)) )
    }

}
