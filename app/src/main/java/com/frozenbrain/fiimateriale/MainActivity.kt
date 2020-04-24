package com.frozenbrain.fiimateriale

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.frozenbrain.fiimateriale.fragment.HowToUseFragment
import com.frozenbrain.fiimateriale.fragment.MainFragment
import com.google.android.material.navigation.NavigationView
import hotchemi.android.rate.AppRate
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    companion object {
        private lateinit var drawer: DrawerLayout
        private const val newWordActivityRequestCode = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        drawer = drawer_layout
        val toggle = ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()

        // TODO Later implement it by yourself
        AppRate.with(this)
            .setInstallDays(0)
            .setLaunchTimes(2)
            .setRemindInterval(3)
            .monitor()
        AppRate.showRateDialogIfMeetsConditions(this)

        registerListeners()

        val navigationView = nav_view
        navigationView.setNavigationItemSelectedListener(this)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, MainFragment()).commit()
            fragment_title.text = getString(R.string.home)
            // when the device is rotated, but i'm too tired to understand
        }

//        ROOM TEST



    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)


    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // TODO later check for current fragment
        // val currentFragment: NavDestination? = findNavController(R.id.fragment_container).currentDestination

        when (item.itemId) {
            R.id.nav_home -> {
                supportFragmentManager.beginTransaction().replace(R.id.fragment_container, MainFragment()).commit()
                fragment_title.text = getString(R.string.home)
            }
            R.id.nav_instructions -> {
                supportFragmentManager.beginTransaction().replace(R.id.fragment_container, HowToUseFragment()).commit()
                fragment_title.text = getString(R.string.how_to_use)
            }
            R.id.nav_contact -> {
                startActivity( Intent(this, FeedbackActivity::class.java) )
            }
//            R.id.nav_test -> {
//                supportFragmentManager.beginTransaction().replace(R.id.fragment_container, TestFragment()).commit()
//                fragment_title.text = getString(R.string.testing_mode)
//            }
            R.id.nav_web_version -> startActivity( Intent(Intent.ACTION_VIEW, Uri.parse("https://fiimateriale.firebaseapp.com")) )
        }
        drawer.closeDrawer(GravityCompat.START)

        return true
    }


    override fun onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    
    private fun registerListeners() {
        goToSite.setOnClickListener {
            startActivity( Intent(Intent.ACTION_VIEW, Uri.parse("https://fiimateriale.firebaseapp.com")) )
        }

    }



}