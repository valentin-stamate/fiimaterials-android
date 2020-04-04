package com.frozenbrain.fiimateriale.data

import com.frozenbrain.fiimateriale.recycler_view.items.CourseItem

data class Semester(val name: String, val uClass: String, val sClass: String, val cClass: String, val per: Int, var courses: List<CourseItem> = arrayListOf()) {}