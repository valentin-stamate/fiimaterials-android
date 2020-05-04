package com.frozenbrain.fiimateriale.recycler_view.view_holders

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.frozenbrain.fiimateriale.R
import com.frozenbrain.fiimateriale.data.Data
import com.frozenbrain.fiimateriale.data.HofPerson
import com.frozenbrain.fiimateriale.data.OnItemClickListener

class HofPersonViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private var personImage: ImageView = view.findViewById(R.id.personImage)
    private var personName: TextView = view.findViewById(R.id.personName)
    private var personBody: TextView = view.findViewById(R.id.personBody)
    private var personRole: TextView = view.findViewById(R.id.personRole)
    private var personButtonAction: Button = view.findViewById(R.id.personAction)

    fun bind(item: HofPerson, clickListenerOn: OnItemClickListener) {
        Glide.with(itemView.context).load(item.imageLink).into(personImage)
        personName.text = item.name
        personBody.text = item.body
        personRole.text = item.role
        personButtonAction.setOnClickListener {
            clickListenerOn.onItemClicked(item, Data.TYPE_HOF_PERSON)
        }
    }

}