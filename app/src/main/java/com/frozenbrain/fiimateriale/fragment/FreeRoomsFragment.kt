package com.frozenbrain.fiimateriale.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.frozenbrain.fiimateriale.R
import com.frozenbrain.fiimateriale.data.Data
import com.frozenbrain.fiimateriale.data.OnItemClickListener
import com.frozenbrain.fiimateriale.recycler_view.RecyclerViewAdapter
import com.frozenbrain.fiimateriale.viewmodel.AppViewModel
import kotlinx.android.synthetic.main.fragment_free_rooms.*

class FreeRoomsFragment : Fragment(), OnItemClickListener {

    private lateinit var viewModel: AppViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_free_rooms, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        promptOnFail.visibility = View.GONE

        val observer = Observer<List<Data>> {
            if (it.isNotEmpty()) {

                view?.findViewById<RecyclerView>(R.id.freeRoomsRecycler)?.apply {
                    layoutManager = LinearLayoutManager(context)
                    adapter = RecyclerViewAdapter(it, this@FreeRoomsFragment)
                }

            } else {
                freeRoomsRecycler.visibility = View.GONE
                promptOnFail.visibility = View.VISIBLE
            }
        }

        viewModel = ViewModelProvider(this).get(AppViewModel::class.java)
        viewModel.onFreeRoomsListInit()
        viewModel.freeRoomsList.observe(viewLifecycleOwner, observer)

    }

}
