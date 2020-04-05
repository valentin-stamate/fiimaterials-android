package com.frozenbrain.fiimateriale

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.frozenbrain.fiimateriale.data.Values
import com.frozenbrain.fiimateriale.data.Year
import com.frozenbrain.fiimateriale.fragment.FirstFragment
import com.frozenbrain.fiimateriale.fragment.SecondFragment
import com.frozenbrain.fiimateriale.recycler_view.OnItemClickListener
import com.frozenbrain.fiimateriale.recycler_view.RecyclerViewAdapter
import com.frozenbrain.fiimateriale.recycler_view.items.Data
import com.frozenbrain.fiimateriale.recycler_view.items.UsefulLinkItem
import com.google.android.material.navigation.NavigationView
import com.google.firebase.firestore.FirebaseFirestore
import com.miguelcatalan.materialsearchview.MaterialSearchView
import hotchemi.android.rate.AppRate
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main_fragment.*
import kotlinx.android.synthetic.main.toolbar.*

class MainActivity : AppCompatActivity(), OnItemClickListener, NavigationView.OnNavigationItemSelectedListener {

    companion object {
        lateinit var searchView: MaterialSearchView
        lateinit var db: FirebaseFirestore
        lateinit var semesterIntent: Intent
        lateinit var usefulLinks: MutableList<Data>
        private lateinit var baseReference: MainActivity
        private lateinit var years: MutableList<Year>
        private var i: Int = 0
        private lateinit var drawer: DrawerLayout
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setIcon(R.drawable.ic_logo)

        drawer = drawer_layout
        val toggle = ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()

        baseReference = this

        db = FirebaseFirestore.getInstance()
        semesterIntent = Intent(this, SemesterActivity::class.java)

        usefulLinks = mutableListOf()
        years = mutableListOf()

        usefulLinkRecycler.visibility = View.GONE

        // TODO Later implement it by yourself
        AppRate.with(this)
            .setInstallDays(0)
            .setLaunchTimes(2)
            .setRemindInterval(3)
            .monitor()
        AppRate.showRateDialogIfMeetsConditions(this)

        initYears()
        changeLayoutYear(i)
        registerListeners()
        fetchData()

//
        val navigationView = nav_view
        navigationView.setNavigationItemSelectedListener(this)

        if (savedInstanceState == null) {
            // when the device is rotated, but i'm too tired to understand
        }

    }

    private fun changeLayoutYear(i: Int) {
        yearTitle.text = years[i].yearTitle

        semOneU.text = years[i].semOne.uClass
        semOneS.text = years[i].semOne.sClass
        semOneC.text = years[i].semOne.cClass

        semTwoU.text = years[i].semTwo.uClass
        semTwoS.text = years[i].semTwo.sClass
        semTwoC.text = years[i].semTwo.cClass

        if (android.os.Build.VERSION.SDK_INT >= 24) {
            semOneP.setProgress(years[i].semOne.per, true)
            semTwoP.setProgress(years[i].semTwo.per, true)
        }
        else {
            semOneP.progress = years[i].semOne.per
            semTwoP.progress = years[i].semTwo.per
        }

    }

    override fun onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
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

                usefulLinkRecycler.apply {
                    layoutManager = LinearLayoutManager(context)
                    adapter = RecyclerViewAdapter(usefulLinks, baseReference)
                }

                usefulLinkRecycler.visibility = View.VISIBLE
                progressBarMain.visibility = View.GONE

            }
            .addOnFailureListener{ exception ->
                Log.d("TAG", "Failure fetching useful links", exception)
                Toast.makeText(baseContext, "Fail to load links", Toast.LENGTH_LONG).show()
            }
    }

    private fun registerListeners() {
        yearOneLeft.setOnClickListener {
            semesterIntent.putExtra("year", years[i].yearTitle)
            semesterIntent.putExtra("semester", Values.FIRST_SEMESTER)

            startActivity(semesterIntent)
        }

        yearOneRight.setOnClickListener {
            semesterIntent.putExtra("year", years[i].yearTitle)
            semesterIntent.putExtra("semester", Values.SECOND_SEMESTER)

            startActivity(semesterIntent)
        }

        goToSite.setOnClickListener {
            startActivity( Intent(Intent.ACTION_VIEW, Uri.parse("https://fiimateriale.firebaseapp.com")) )
        }

    }

    override fun onItemClicked(item: Data) {
        val it = item as UsefulLinkItem
        startActivity( Intent(Intent.ACTION_VIEW, Uri.parse(item.link)) )
    }

    fun onLeftArrowClicked(view: View) {
        if (i - 1 >= 0) {
            yearTitle.text = years[--i].yearTitle
            changeLayoutYear(i)
        }
    }

    fun onRightArrowClicked(view: View) {
        if (i + 1 <= 2) {
            yearTitle.text = years[++i].yearTitle
            changeLayoutYear(i)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_message -> supportFragmentManager.beginTransaction().replace(R.id.fragment_container, FirstFragment()).commit()
            R.id.nav_chat -> supportFragmentManager.beginTransaction().replace(R.id.fragment_container, SecondFragment()).commit()
        }

        drawer.closeDrawer(GravityCompat.START)

        return true
    }

}