package com.frozenbrain.fiimateriale.data

import com.google.firebase.Timestamp
import com.google.firebase.database.IgnoreExtraProperties
import com.google.firebase.database.ServerValue
import com.google.firebase.firestore.Exclude
import java.util.*

@IgnoreExtraProperties
class Feedback: Data {
    lateinit var name: String;
    lateinit var subject: String
    lateinit var message: String
    var timestamp: Timestamp = Timestamp.now()
    var solved: Boolean = false

    constructor() {}

    constructor(name: String, subject: String, message: String) {
        this.name = name
        this.subject = subject
        this.message = message
    }

    @Exclude
    override fun getType(): Int {
        return Data.TYPE_FEEDBACK
    }
}