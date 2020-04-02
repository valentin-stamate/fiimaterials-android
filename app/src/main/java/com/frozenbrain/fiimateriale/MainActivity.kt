package com.frozenbrain.fiimateriale

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.frozenbrain.fiimateriale.parcelables.Semester
import com.frozenbrain.fiimateriale.recycler_view.OnItemClickListener
import com.frozenbrain.fiimateriale.recycler_view.RecyclerViewAdapter
import com.frozenbrain.fiimateriale.recycler_view.items.ClassItem
import com.frozenbrain.fiimateriale.recycler_view.items.Data
import com.frozenbrain.fiimateriale.recycler_view.items.UsefulLinkItem
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.miguelcatalan.materialsearchview.MaterialSearchView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*

class MainActivity : AppCompatActivity(), OnItemClickListener {

    lateinit var searchView: MaterialSearchView
    lateinit var db: DatabaseReference
    lateinit var semesterIntent: Intent
    lateinit var usefulLinks: MutableList<Data>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setIcon(R.drawable.ic_logo)

        db = FirebaseDatabase.getInstance().reference.child("Years")
        semesterIntent = Intent(this, SemesterActivity::class.java)

        usefulLinks = mutableListOf()
        usefulLinks.add(UsefulLinkItem("Dkhukashda", "kjdhasdnkas"))
        usefulLinks.add(UsefulLinkItem("Dkhukashda2", "kjdhasdnkas"))
        usefulLinks.add(UsefulLinkItem("Dkhukashda3", "kjdhasdnkas"))
        usefulLinks.add(UsefulLinkItem("Dkhukashda4", "kjdhasdnkas"))
        usefulLinks.add(UsefulLinkItem("Dkhukashda5", "kjdhasdnkas"))
        usefulLinks.add(UsefulLinkItem("Dkhukashda6", "kjdhasdnkas"))


        val ref = this

        usefulLinkRecycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = RecyclerViewAdapter(usefulLinks, ref)
        }

        registerListeners()

    }

    private fun registerListeners() {
        yearOneLeft.setOnClickListener {
            semesterIntent.putExtra("semester", Semester("First Year", "First Year/First Semester"))
            startActivity(semesterIntent)
        }

        yearOneRight.setOnClickListener {
            semesterIntent.putExtra("semester", Semester("First Year", "First Year/Second Semester"))
            startActivity(semesterIntent)
        }



    }

    override fun onItemClicked(item: Data) {
        val it = item as UsefulLinkItem
        startActivity( Intent(Intent.ACTION_VIEW, Uri.parse(it.url)) )
    }

}
//ref.setValue("ma-ta") { error, ref ->
//    if (error != null) {
//        Toast.makeText(this, error.message, Toast.LENGTH_LONG).show()
//    }
//}