package com.frozenbrain.fiimateriale.recycler_view.items

class UsefulLinkItem(val title: String = "", val link: String = ""): Data {
    override fun getType(): Int {
        return Data.TYPE_USEFUL_LINK
    }
}