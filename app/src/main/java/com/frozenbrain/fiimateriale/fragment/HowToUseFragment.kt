package com.frozenbrain.fiimateriale.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.frozenbrain.fiimateriale.R

class HowToUseFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_how_to_use, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val imageView = view?.findViewById<ImageView>(R.id.instructionsImage)
        if (imageView != null) {
            Glide.with(this).load(R.drawable.svg_instructions_dark).into(imageView)
        }

    }

}