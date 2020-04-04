package com.frozenbrain.fiimateriale.recycler_view.items

class CourseItem(val name: String = "", val short: String = "", val credits: Int = -1, val lastUpdated: String = "", val megaLink: String = "", val githubLink: String = ""): Data {
    override fun getType(): Int {
        return Data.TYPE_CLASS
    }
}