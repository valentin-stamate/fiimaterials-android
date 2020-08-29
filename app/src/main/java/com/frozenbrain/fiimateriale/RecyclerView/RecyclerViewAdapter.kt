package com.frozenbrain.fiimateriale.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.frozenbrain.fiimateriale.R
import com.frozenbrain.fiimateriale.recyclerview.model.IntroductionItem
import com.frozenbrain.fiimateriale.recyclerview.viewholders.IntroductionViewHolder

class RecyclerViewAdapter(private val list: List<Any>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return IntroductionViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.introduction_item, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = list[position]
        (holder as IntroductionViewHolder).bind(item as IntroductionItem)
    }

    override fun getItemCount(): Int {
        return list.size
    }

}