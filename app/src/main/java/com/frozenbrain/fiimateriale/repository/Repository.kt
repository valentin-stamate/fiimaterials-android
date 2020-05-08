package com.frozenbrain.fiimateriale.repository

import android.app.Person
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.frozenbrain.fiimateriale.FreeRoomsApi
import com.frozenbrain.fiimateriale.LaunchScreenActivity
import com.frozenbrain.fiimateriale.data.*
import com.google.firebase.database.*
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.withContext
import java.lang.Exception

object Repository {
    private val usefulLinkList: MutableList<Data> = mutableListOf()
    private val aboutDataList: MutableList<AboutDataItem> = mutableListOf()
    private val hofPersonList: MutableList<Data> = mutableListOf()
    private val feedbackPersonList_: MutableList<Data> = mutableListOf()
    private var freeRoomsList: List<Data> = listOf()

    var dataFetch = 0 // yeah, i didn't find any better solution

    suspend fun fetchUsefulLinks(databaseReference: DatabaseReference) {
        usefulLinkList.clear()

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (linkItem in dataSnapshot.children) {
                    val item = toUsefulLinkItem(linkItem)
                    usefulLinkList.add(item)
                }
                dataFetch++
            }
            override fun onCancelled(p0: DatabaseError) {}
        }

        databaseReference.addValueEventListener(postListener)
    }

    suspend fun fetchAboutDataList(databaseReference: DatabaseReference) {
        aboutDataList.clear()

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                for (child in dataSnapshot.children) {
                    val contentItem = toContentItem(child)
                    aboutDataList.add(contentItem)
                }
                dataFetch++
            }
            override fun onCancelled(databaseError: DatabaseError) {}
        }
        databaseReference.addValueEventListener(postListener)
    }

    suspend fun fetchHofPersons(databaseReference: DatabaseReference) {
        hofPersonList.clear()

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (childItem in dataSnapshot.children) {
                    val item = toPersonItem(childItem)
                    hofPersonList.add(item)
                }
                dataFetch++
            }
            override fun onCancelled(databaseError: DatabaseError) {}
        }
        databaseReference.addValueEventListener(postListener)
    }

    suspend fun fetchFeedback(db: FirebaseFirestore) {
        feedbackPersonList_.clear()

        db.collection("Feedback").orderBy("timestamp", Query.Direction.ASCENDING).get().addOnSuccessListener {
            for (document in it) {
                val item = document.toObject(Feedback::class.java)
                feedbackPersonList_.add(item)
            }
            // dataFetch++
            listenToNewFeedback(db)
        }
    }
    private fun listenToNewFeedback(db: FirebaseFirestore) {
        var firstInit = true
        db.collection("Feedback").orderBy("timestamp", Query.Direction.DESCENDING).limit(1)
            .addSnapshotListener { value, e ->
                if (e != null) {
                    return@addSnapshotListener
                }
                if (!firstInit) {
                    for (doc in value!!.documentChanges) {
                        if (doc.type == DocumentChange.Type.ADDED) {
                            val fb = doc.document.toObject(Feedback::class.java)
                            feedbackPersonList_.add(fb)
                        }
                    }
                }
                firstInit = false
            }
    }

    suspend fun fetchFreeRoomsList() {
        var list: List<Data> = listOf()
        list = FreeRoomsApi.getFreeRooms()

        withContext(Main) {
            freeRoomsList = list
        }
    }

    // using coroutines oh yeah
    fun getUsefulLinks(): MutableList<Data> {
        return usefulLinkList
    }
    fun getAboutDataList(): MutableList<AboutDataItem> {
        return aboutDataList
    }
    fun getHofPersons(): MutableList<Data> {
        return hofPersonList
    }
    fun getFeedbackList(): MutableLiveData<MutableList<Data>> {
        val liveData = MutableLiveData<MutableList<Data>>()
        liveData.value = feedbackPersonList_
        return liveData
    }
    fun getFreeRoomsList(): List<Data> {
        return freeRoomsList
    }

    private fun toUsefulLinkItem(childItem: DataSnapshot): UsefulLinkItem {
        val title = childItem.child("title").value.toString()
        val link = childItem.child("link").value.toString()
        return UsefulLinkItem(title, link)
    }
    private fun toContentItem(snapshot: DataSnapshot): AboutDataItem {
        val title = snapshot.child("title").value.toString()
        val body = snapshot.child("body").value.toString()
        return AboutDataItem(title, body)
    }
    private fun toPersonItem(snapshot: DataSnapshot): HofPerson {
        val name = snapshot.child("title").value.toString()
        val body = snapshot.child("body").value.toString()
        val role = "Role: " + snapshot.child("role").value.toString()
        val imageLink = snapshot.child("avatarLink").value.toString()
        val link = snapshot.child("link").value.toString()
        return HofPerson(name, body, role, imageLink, link)
    }

    fun destroyListeners() {

    }
}