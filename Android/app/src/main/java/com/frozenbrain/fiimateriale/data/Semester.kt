package com.frozenbrain.fiimateriale.data

data class Semester(val name: String, val uClass: String, val sClass: String, val cClass: String, val per: Int, var courses: List<CourseItem> = arrayListOf()) {}