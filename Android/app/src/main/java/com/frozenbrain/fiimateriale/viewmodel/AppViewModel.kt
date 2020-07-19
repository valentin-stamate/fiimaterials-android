package com.frozenbrain.fiimateriale.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.frozenbrain.fiimateriale.data.AboutDataItem
import com.frozenbrain.fiimateriale.data.Data
import com.frozenbrain.fiimateriale.data.HofPerson
import com.frozenbrain.fiimateriale.repository.Repository

class AppViewModel : ViewModel() {
    val usefulLinkList: MutableLiveData<MutableList<Data>> by lazy {
        MutableLiveData<MutableList<Data>>()
    }
    val aboutDataList: MutableLiveData<MutableList<AboutDataItem>> by lazy {
        MutableLiveData<MutableList<AboutDataItem>>()
    }
    val hofPersonList: MutableLiveData<MutableList<Data>> by lazy {
        MutableLiveData<MutableList<Data>>()
    }
    val freeRoomsList: MutableLiveData<List<Data>> by lazy {
        MutableLiveData<List<Data>>()
    }

    lateinit var feedbackList: MutableLiveData<MutableList<Data>>

    fun onUsefulLinkListInit() {
        usefulLinkList.value = Repository.getUsefulLinks()
    }

    fun onAboutDataListInit() {
        aboutDataList.value = Repository.getAboutDataList()
    }

    fun onHofPersonListInit() {
        hofPersonList.value = Repository.getHofPersons()
    }

    fun onFeedbackListInit() {
        feedbackList = Repository.getFeedbackList()
    }

    fun onFreeRoomsListInit() {
        freeRoomsList.value = Repository.getFreeRoomsList()
    }

}