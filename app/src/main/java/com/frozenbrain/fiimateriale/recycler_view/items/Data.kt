package com.frozenbrain.fiimateriale.recycler_view.items

interface Data {
    companion object {
        const val TYPE_CLASS = 0
        const val TYPE_USEFUL_LINK = 1
        const val TITLE = 2
    }
    fun getType(): Int
}