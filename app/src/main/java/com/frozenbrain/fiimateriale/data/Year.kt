package com.frozenbrain.fiimateriale.data

class Year(val yearTitle: String) {
    lateinit var semOne: Semester
    lateinit var semTwo: Semester

    fun initSem(semester: String, u: Int, s: Int, c: Int, p: Int): Year {
        if (semester == Values.FIRST_SEMESTER)
            semOne = Semester(Values.FIRST_SEMESTER, "C : $u", "O : $s", "S : $c", p)
        else
            semTwo = Semester(Values.SECOND_SEMESTER, "C : $u", "O : $s", "S : $c", p)
        return this
    }

}