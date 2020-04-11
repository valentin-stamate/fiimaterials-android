package com.frozenbrain.fiimateriale.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.frozenbrain.fiimateriale.R
import com.frozenbrain.fiimateriale.SemesterActivity
import com.frozenbrain.fiimateriale.data.Values
import com.frozenbrain.fiimateriale.data.Year
import com.frozenbrain.fiimateriale.recycler_view.OnItemClickListener
import com.frozenbrain.fiimateriale.recycler_view.RecyclerViewAdapter
import com.frozenbrain.fiimateriale.recycler_view.items.Data
import com.frozenbrain.fiimateriale.recycler_view.items.UsefulLinkItem
import com.google.firebase.firestore.FirebaseFirestore
import hotchemi.android.rate.AppRate
import java.lang.Exception

class MainFragment: Fragment(), OnItemClickListener {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    companion object {
        lateinit var db: FirebaseFirestore
        lateinit var semesterIntent: Intent
        lateinit var usefulLinks: MutableList<Data>
        private lateinit var years: MutableList<Year>
        private var i: Int = 0
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        db = FirebaseFirestore.getInstance()
        semesterIntent = Intent(context, SemesterActivity::class.java)

        usefulLinks = mutableListOf()
        years = mutableListOf()


        view?.findViewById<RecyclerView>(R.id.usefulLinkRecycler)?.visibility = View.GONE

        db = FirebaseFirestore.getInstance()
        semesterIntent = Intent(context, SemesterActivity::class.java)

        usefulLinks = mutableListOf()
        years = mutableListOf()


        // TODO Later implement it by yourself
        AppRate.with(view?.context)
            .setInstallDays(0)
            .setLaunchTimes(2)
            .setRemindInterval(3)
            .monitor()
        AppRate.showRateDialogIfMeetsConditions(activity)

        initYears()
        changeLayoutYear(i)
        registerListeners()
        fetchData()

    }

    private fun changeLayoutYear(i: Int) {
        view?.findViewById<TextView>(R.id.yearTitle)?.text = years[i].yearTitle

        view?.findViewById<TextView>(R.id.semOneU)?.text = years[i].semOne.uClass
        view?.findViewById<TextView>(R.id.semOneS)?.text = years[i].semOne.sClass
        view?.findViewById<TextView>(R.id.semOneC)?.text = years[i].semOne.cClass

        view?.findViewById<TextView>(R.id.semTwoU)?.text = years[i].semTwo.uClass
        view?.findViewById<TextView>(R.id.semTwoS)?.text = years[i].semTwo.sClass
        view?.findViewById<TextView>(R.id.semTwoC)?.text = years[i].semTwo.cClass

        if (android.os.Build.VERSION.SDK_INT >= 24) {
            view?.findViewById<ProgressBar>(R.id.semOneP)?.setProgress(years[i].semOne.per, true)
            view?.findViewById<ProgressBar>(R.id.semTwoP)?.setProgress(years[i].semTwo.per, true)
        }
        else {
            view?.findViewById<ProgressBar>(R.id.semOneP)?.progress = years[i].semOne.per
            view?.findViewById<ProgressBar>(R.id.semTwoP)?.progress = years[i].semTwo.per
        }

    }


    private fun initYears() {
        years.add(Year(Values.FIRST_YEAR))
        years.add(Year(Values.SECOND_YEAR))
        years.add(Year(Values.THIRD_YEAR))

        years[0].initSem(Values.FIRST_SEMESTER, 6, 0, 3, 50).initSem(Values.SECOND_SEMESTER, 6, 0, 4, 60)
        years[1].initSem(Values.FIRST_SEMESTER, 5, 4, 4, 80).initSem(Values.SECOND_SEMESTER, 5, 4, 3, 50)
        years[2].initSem(Values.FIRST_SEMESTER, 4, 8, 2, 70).initSem(Values.SECOND_SEMESTER, 3, 10, 3, 80)
        // TODO later use database for fetching these too
    }

    private fun fetchData() {
        db.collection("Useful Links").get()
            .addOnSuccessListener { querySnapshot ->
                val listArray = querySnapshot.toObjects(UsefulLinkItem::class.java)

                for (itemL in listArray) {
                    usefulLinks.add(itemL as UsefulLinkItem)
                }

                view?.findViewById<RecyclerView>(R.id.usefulLinkRecycler)?.apply {
                    layoutManager = LinearLayoutManager(context)
                    adapter = RecyclerViewAdapter(usefulLinks, this@MainFragment)
                }

                view?.findViewById<RecyclerView>(R.id.usefulLinkRecycler)?.visibility = View.VISIBLE
                view?.findViewById<ProgressBar>(R.id.progressBarMain)?.visibility = View.GONE

            }
            .addOnFailureListener{ exception ->
                Log.d("TAG", "Failure fetching useful links", exception)
                Toast.makeText(context, "Fail to load links", Toast.LENGTH_LONG).show()
            }
    }

    private fun registerListeners() {

        view?.findViewById<CardView>(R.id.yearOneLeft)?.setOnClickListener {

            semesterIntent.putExtra("year", years[i].yearTitle)
            semesterIntent.putExtra("semester", Values.FIRST_SEMESTER)

            startActivity(semesterIntent)
        }

        view?.findViewById<CardView>(R.id.yearOneRight)?.setOnClickListener {
            semesterIntent.putExtra("year", years[i].yearTitle)
            semesterIntent.putExtra("semester", Values.SECOND_SEMESTER)

            startActivity(semesterIntent)
        }

        view?.findViewById<ImageButton>(R.id.leftSwitcher)?.setOnClickListener {
            onLeftArrowClicked()
        }
        view?.findViewById<ImageButton>(R.id.rightSwitcher)?.setOnClickListener {
            onRightArrowClicked()
        }

    }

    override fun onItemClicked(item: Data, type: Int) {
        item as UsefulLinkItem
        try {
            startActivity( Intent(Intent.ACTION_VIEW, Uri.parse(item.link)) )
        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun onLeftArrowClicked() {
        if (i - 1 >= 0) {
            view?.findViewById<TextView>(R.id.yearTitle)?.text = years[--i].yearTitle
            changeLayoutYear(i)
        }
    }
    private fun onRightArrowClicked() {
        if (i + 1 <= 2) {
            view?.findViewById<TextView>(R.id.yearTitle)?.text = years[++i].yearTitle
            changeLayoutYear(i)
        }
    }
}