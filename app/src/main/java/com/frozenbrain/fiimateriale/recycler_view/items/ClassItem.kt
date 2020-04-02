package com.frozenbrain.fiimateriale.recycler_view.items

class ClassItem(val name: String, val short: String ,val credits: Int, val lastUpdated: String, val megaLink: String, val githubLink: String = ""): Data {
    override fun getType(): Int {
        return Data.TYPE_CLASS
    }
}