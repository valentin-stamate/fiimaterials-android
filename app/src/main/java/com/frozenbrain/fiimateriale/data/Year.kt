package com.frozenbrain.fiimateriale.data

class Year(val yearTitle: String) {
    lateinit var semOne: Semester
    lateinit var semTwo: Semester

    fun initSem(semester: String, u: Int, s: Int, c: Int, p: Int): Year {
        if (semester == Values.FIRST_SEMESTER)
            semOne = Semester(Values.FIRST_SEMESTER, "U : $u", "S : $s", "C : $c", p)
        else
            semTwo = Semester(Values.SECOND_SEMESTER, "U : $u", "S : $s", "C : $c", p)
        return this
    }

}