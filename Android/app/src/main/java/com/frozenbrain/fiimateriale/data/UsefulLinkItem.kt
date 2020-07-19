package com.frozenbrain.fiimateriale.data

class UsefulLinkItem(val title: String = "", val link: String = ""):
    Data {
    override fun getType(): Int {
        return Data.TYPE_USEFUL_LINK
    }
}