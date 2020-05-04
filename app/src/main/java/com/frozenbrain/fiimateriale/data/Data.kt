package com.frozenbrain.fiimateriale.data

interface Data {
    companion object {
        const val TYPE_CLASS = 0
        const val TYPE_USEFUL_LINK = 1
        const val TYPE_TITLE = 2
        const val TYPE_HOF_PERSON = 3
        const val TYPE_FEEDBACK = 4
    }
    fun getType(): Int
}