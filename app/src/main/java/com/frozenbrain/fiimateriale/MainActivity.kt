package com.frozenbrain.fiimateriale

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.miguelcatalan.materialsearchview.MaterialSearchView
import com.miguelcatalan.materialsearchview.MaterialSearchView.SearchViewListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*


class MainActivity : AppCompatActivity() {

    companion object  {
        lateinit var searchView: MaterialSearchView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setIcon(R.drawable.ic_logo)

        yearOneLeft.setOnClickListener {
            val intent = Intent(this, SemesterActivity::class.java)
            startActivity(intent)
        }

//        searchView = search_view as MaterialSearchView
//        searchView.setVoiceSearch(false)
//        searchView.setSuggestions(resources.getStringArray(R.array.query_suggestions));
//
//        searchView.setOnQueryTextListener(object : MaterialSearchView.OnQueryTextListener {
//
//
//            override fun onQueryTextSubmit(query: String): Boolean {
//                //Do some magic
//                searchView.visibility = View.VISIBLE // TODO find a wae to fix this
//                return false
//            }
//
//            override fun onQueryTextChange(newText: String): Boolean {
//                //Do some magic
//                return false
//            }
//        })
//
//        searchView.setOnSearchViewListener(object : SearchViewListener {
//            override fun onSearchViewShown() {
//
//            }
//
//            override fun onSearchViewClosed() {
//                //Do some magic
//            }
//        })
//
//
//        searchView.closeSearch()

        //initImages()

    }

    private fun initImages() {
       //Glide.with(this).load(R.drawable.year_two_bck).into(imageViewSecondYear)
    }

//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        menuInflater.inflate(R.menu.menu_main, menu)
//
//        val menuItem = menu.findItem(R.id.action_search)
//
//        searchView.setMenuItem(menuItem)
//        return true
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//
//        if (item.itemId == R.id.action_search) {
//            search_view.visibility = View.VISIBLE
//            return true
//        }
//        return super.onOptionsItemSelected(item);
//    }
}
