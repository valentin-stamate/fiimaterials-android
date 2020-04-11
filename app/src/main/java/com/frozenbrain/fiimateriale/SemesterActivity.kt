package com.frozenbrain.fiimateriale

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.frozenbrain.fiimateriale.recycler_view.OnItemClickListener
import com.frozenbrain.fiimateriale.recycler_view.RecyclerViewAdapter
import com.frozenbrain.fiimateriale.recycler_view.items.CourseItem
import com.frozenbrain.fiimateriale.recycler_view.items.Data
import com.frozenbrain.fiimateriale.recycler_view.items.TitleItem
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_semester.*

class SemesterActivity : AppCompatActivity(), OnItemClickListener {

    private lateinit var db: DatabaseReference
    private lateinit var list: MutableList<Data>
    private lateinit var classReference: SemesterActivity

    companion object {
        lateinit var year: String
        lateinit var semester: String
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_semester)

        toolbar_semester.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp)
        toolbar_semester.setNavigationOnClickListener{
            finish()
        }

        year = intent.getStringExtra("year") as String
        semester = intent.getStringExtra("semester") as String
        toolbar_semester.title = year

        //reference.child("Years/$year/$semester")

        db = FirebaseDatabase.getInstance().reference.child("Years/$year/$semester")
        list = mutableListOf()

        classReference = this
        getFromDatabase()

        recyclerView.visibility = View.GONE
        progressBar.visibility = View.VISIBLE

    }

    private fun getFromDatabase() {

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                for (courseType in dataSnapshot.children) {

                    list.add( TitleItem(courseType.key.toString()) )

                    for (childItem in courseType.children) {
                        val item = toClassItem(childItem)
                        list.add(item)
                    }
                }

                progressBar.visibility = View.GONE
                recyclerView.visibility = View.VISIBLE

                recyclerView.apply {
                    layoutManager = LinearLayoutManager(context)
                    adapter = RecyclerViewAdapter(list, classReference)
                }

            }
            override fun onCancelled(databaseError: DatabaseError) {
                Log.w("Error Fetching", "loadPost:onCancelled", databaseError.toException())
                Toast.makeText(baseContext, "Failed to load classes.", Toast.LENGTH_SHORT).show()
            }
        }
        db.addValueEventListener(postListener)
    }

    private fun toClassItem(childItem: DataSnapshot): CourseItem {
        val name = childItem.child("name").value.toString()
        val short = childItem.child("short").value.toString()
        val credits = childItem.child("credits").value.toString().toInt()
        val lastUpdated = childItem.child("lastUpdated").value.toString()
        val megaLink = childItem.child("megaLink").value.toString()
        val sitePage = childItem.child("sitePage").value.toString()
        val sitePassword = childItem.child("sitePassword").value.toString()

        return CourseItem(name, short, credits, megaLink, sitePage, sitePassword)
    }

    override fun onItemClicked(item: Data, type: Int) {
        val it = item as CourseItem
        when (type) {
            0 -> {
                if (it.megaLink.isNotEmpty()) {
                    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(it.megaLink)))
                } else {
                    Toast.makeText(this, "There is no data for this class..yet", Toast.LENGTH_SHORT).show()
                }
            }
            1 -> {
                if(it.sitePage.isNotEmpty()) {
                    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(it.sitePage)))
                    if (item.sitePassword.isNotEmpty())
                        Toast.makeText(this, item.sitePassword, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

}
