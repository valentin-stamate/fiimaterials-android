package com.frozenbrain.fiimateriale

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.frozenbrain.fiimateriale.RecyclerView.RecyclerViewAdapter
import com.frozenbrain.fiimateriale.RecyclerView.RecyclerViewItem
import com.frozenbrain.fiimateriale.semester.ClassItem
import com.frozenbrain.fiimateriale.semester.Semester
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_semester.*

class SemesterActivity : AppCompatActivity() {

    private lateinit var db: DatabaseReference
    private lateinit var semester: Semester
    private lateinit var list: MutableList<RecyclerViewItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_semester)

        toolbar_semester.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp)
        toolbar_semester.setNavigationOnClickListener{
            finish() // TODO FInd a wae to get the item id
        }

        semester = intent.getParcelableExtra("semester") as Semester
        semester_title.text = semester.name

        db = FirebaseDatabase.getInstance().reference.child("Years/${semester.dataURL}")
        list = mutableListOf()

        getFromDatabase()

        recyclerView.visibility = View.GONE
        progressBar.visibility = View.VISIBLE
    }

    private fun getFromDatabase() {
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {


                for (childItem in dataSnapshot.children) {
                    //val data = toClassItem(childItem)
                    val item = RecyclerViewItem(childItem.child("name").value.toString())

                    list.add(item)

                    progressBar.visibility = View.GONE
                    recyclerView.visibility = View.VISIBLE

                }

                Toast.makeText(baseContext, "Job Done", Toast.LENGTH_LONG).show()
                recyclerView.apply {
                    layoutManager = LinearLayoutManager(context)
                    adapter = RecyclerViewAdapter(list)
                }

            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w("Blea", "loadPost:onCancelled", databaseError.toException())
                // [START_EXCLUDE]
                Toast.makeText(baseContext, "Failed to load post.",
                    Toast.LENGTH_SHORT).show()
                // [END_EXCLUDE]
            }
        }
        (db.child("Compulsory Courses")).addValueEventListener(postListener)
    }

    private fun toClassItem(childItem: DataSnapshot): ClassItem {
        val name = childItem.child("name").value.toString()
        val short = childItem.child("short").value.toString()
        val credits = childItem.child("credits").value.toString()
        val lastUpdated = childItem.child("lastUpdated").value.toString()
        val megaLink = childItem.child("megaLink").value.toString()

        return ClassItem(name, short, credits, lastUpdated, megaLink)
    }


}
