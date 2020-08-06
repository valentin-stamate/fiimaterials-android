package com.frozenbrain.fiimateriale.data

class HofPerson(val name: String, val body: String, val role: String, val imageLink: String, val link: String): Data {
    override fun getType(): Int {
        return Data.TYPE_HOF_PERSON
    }

}