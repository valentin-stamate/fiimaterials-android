package com.frozenbrain.fiimateriale

import CustomSearchAdapter
import android.app.SearchManager
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity() {

    companion object {
        lateinit var arrayAdapter: ArrayAdapter<*>
        //lateinit var arrayList: ArrayList<String>
        lateinit var listView: ListView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        listView = searchList;
        listView.setOnItemClickListener { parent, view, position, id ->
            Toast.makeText(this, "Clicked search result" + (view as TextView).text, Toast.LENGTH_SHORT).show()
        }

        //arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList)

        handleSearch()


        //
//        arrayList = ArrayList()
//        arrayList.add("ana")
//        arrayList.add("are")
//        arrayList.add("doua")
//        arrayList.add("mere")

        Toast.makeText(this, "Lorem ipsum dolor sit amed", Toast.LENGTH_SHORT).show()
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent) // >>>>>>>>>>
        setIntent(intent)
        handleSearch()
    }

    private fun handleSearch() {
        val intent = intent

        if (Intent.ACTION_SEARCH == intent.action) {
            val searchedString = intent.getStringArrayExtra(SearchManager.QUERY)

            val adapter = CustomSearchAdapter(
                this,
                android.R.layout.simple_dropdown_item_1line,
                StoresData.filterData(searchedString as String?)
            )

            listView.adapter = adapter
            Toast.makeText(this, "From HandleSearch()", Toast.LENGTH_SHORT).show()

        } else if (Intent.ACTION_VIEW == intent.action) {
            val selectedID = intent.dataString

            Toast.makeText(this, "Selected id" + selectedID, Toast.LENGTH_SHORT).show()
        }

    }

}
