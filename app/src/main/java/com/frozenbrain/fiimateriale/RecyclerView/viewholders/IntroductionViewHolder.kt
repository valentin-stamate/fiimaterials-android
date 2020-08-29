package com.frozenbrain.fiimateriale.recyclerview.viewholders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.frozenbrain.fiimateriale.R
import com.frozenbrain.fiimateriale.recyclerview.model.IntroductionItem

class IntroductionViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private val title: TextView = view.findViewById(R.id.introduction_title)
    private val body: TextView = view.findViewById(R.id.introduction_body)

    fun bind(item: IntroductionItem) {
        title.text = item.title
        body.text = item.body
    }
}