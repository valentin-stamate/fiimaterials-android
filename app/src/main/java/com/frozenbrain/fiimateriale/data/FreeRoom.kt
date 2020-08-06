package com.frozenbrain.fiimateriale.data

class FreeRoom(val className: String, val freeday: String, val freeHour: String): Data {
    override fun getType(): Int {
        return Data.TYPE_FREE_DAY
    }
}