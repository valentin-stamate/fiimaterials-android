package com.frozenbrain.fiimateriale.recycler_view.items

class TitleItem(val title: String): Data {
    override fun getType(): Int {
        return Data.TITLE
    }
}