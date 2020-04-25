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
import com.frozenbrain.fiimateriale.recycler_view.items.TitleItem
import com.frozenbrain.fiimateriale.recycler_view.items.UsefulLinkItem
import com.google.firebase.database.*
import hotchemi.android.rate.AppRate
import kotlinx.android.synthetic.main.activity_semester.*
import java.lang.Exception

class MainFragment: Fragment(), OnItemClickListener {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    companion object {
        lateinit var db: DatabaseReference
        lateinit var semesterIntent: Intent
        lateinit var usefulLinkList: MutableList<Data>
        private lateinit var years: MutableList<Year>
        private var i: Int = 0
        private lateinit var reference: MainFragment
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        db = FirebaseDatabase.getInstance().reference.child("Useful Links")
        semesterIntent = Intent(context, SemesterActivity::class.java)

        usefulLinkList = mutableListOf()
        years = mutableListOf()


        view?.findViewById<RecyclerView>(R.id.usefulLinkRecycler)?.visibility = View.GONE

        semesterIntent = Intent(context, SemesterActivity::class.java)

        // TODO Later implement it by yourself
        AppRate.with(view?.context)
            .setInstallDays(0)
            .setLaunchTimes(2)
            .setRemindInterval(3)
            .monitor()
        AppRate.showRateDialogIfMeetsConditions(activity)

        reference = this

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
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                for (linkItem in dataSnapshot.children) {
                    val item = toUsefulLinkItem(linkItem)
                    usefulLinkList.add(item)
                }
                onRecyclerViewInit()
            }
            override fun onCancelled(databaseError: DatabaseError) {
                Log.w("Error Fetching", "loadPost:onCancelled", databaseError.toException())
                Toast.makeText(context, "Failed to load classes.", Toast.LENGTH_SHORT).show()
            }
        }
        db.addValueEventListener(postListener)

    }

    private fun toUsefulLinkItem(childItem: DataSnapshot): UsefulLinkItem {
        val title = childItem.child("title").value.toString()
        val link = childItem.child("link").value.toString()

        return UsefulLinkItem(title, link)
    }

    private fun onRecyclerViewInit() {
        view?.findViewById<RecyclerView>(R.id.usefulLinkRecycler)?.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = RecyclerViewAdapter(usefulLinkList, reference)
        }

        view?.findViewById<RecyclerView>(R.id.usefulLinkRecycler)?.visibility = View.VISIBLE
        view?.findViewById<ProgressBar>(R.id.progressBarMain)?.visibility = View.GONE
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
            Toast.makeText(context, "" + Uri.parse(item.link), Toast.LENGTH_SHORT).show()
            // startActivity( Intent(Intent.ACTION_VIEW, Uri.parse(item.link)) )
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