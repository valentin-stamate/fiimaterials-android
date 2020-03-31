package com.frozenbrain.fiimateriale

import android.os.Parcel
import android.os.Parcelable

class Semester(var name: String, var dataURL: String): Parcelable {
    constructor(parcel: Parcel): this(
        parcel.readString() as String,
        parcel.readString() as String
    ) {}

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(dataURL)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Semester> {
        override fun createFromParcel(parcel: Parcel): Semester {
            return Semester(parcel)
        }

        override fun newArray(size: Int): Array<Semester?> {
            return arrayOfNulls(size)
        }
    }
}