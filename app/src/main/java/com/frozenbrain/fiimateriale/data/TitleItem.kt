package com.frozenbrain.fiimateriale.data

class TitleItem(val title: String):
    Data {
    override fun getType(): Int {
        return Data.TYPE_TITLE
    }
}