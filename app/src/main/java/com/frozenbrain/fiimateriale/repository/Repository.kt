package com.frozenbrain.fiimateriale.repository

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.frozenbrain.fiimateriale.item.AboutDataItem
import com.frozenbrain.fiimateriale.item.HofPerson
import com.frozenbrain.fiimateriale.recycler_view.items.Data
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

object Repository {
    private lateinit var usefulLinkList: MutableList<Data>
    private lateinit var aboutText: MutableList<AboutDataItem>
    private lateinit var hallOfFamePersons: MutableList<HofPerson>

    suspend fun fetchUsefulLinks(dataSnapshot: DataSnapshot) {
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (linkItem in dataSnapshot.children) {
                    val item = toUsefulLinkItem(linkItem)
                    usefulLinkList.add(item)
                }
            }
        }
    }

    suspend fun fetchAboutTexts() {

    }

    suspend fun fetchHOFPersons() {

    }

    // using coroutines oh yeah
    fun getUsefulLinks(): MutableLiveData<MutableList<Data>> {
        TODO()
    }

    fun getAboutText(): MutableLiveData<MutableList<AboutDataItem>> {
        TODO()
    }

    fun getHofPersons(): MutableLiveData<MutableList<AboutDataItem>> {
        TODO()
    }


}