package com.frozenbrain.fiimateriale.data
import com.frozenbrain.fiimateriale.data.Data

interface OnItemClickListener {
    fun onItemClicked(item: Data, type: Int) {}
}