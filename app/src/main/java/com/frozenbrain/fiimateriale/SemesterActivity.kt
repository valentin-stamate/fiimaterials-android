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
import com.frozenbrain.fiimateriale.recycler_view.items.ClassItem
import com.frozenbrain.fiimateriale.parcelables.Semester
import com.frozenbrain.fiimateriale.recycler_view.items.Data
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_semester.*

class SemesterActivity : AppCompatActivity(), OnItemClickListener {

    private lateinit var db: DatabaseReference
    private lateinit var semester: Semester
    private lateinit var list: MutableList<ClassItem>
    private lateinit var classReference: SemesterActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_semester)

        toolbar_semester.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp)
        toolbar_semester.setNavigationOnClickListener{
            finish() // TODO FInd a wae to get the item id
        }

        semester = intent.getParcelableExtra("semester") as Semester
        toolbar_semester.title = semester.name

        db = FirebaseDatabase.getInstance().reference.child("Years/${semester.dataURL}")
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
                    list.add(
                        ClassItem(
                            courseType.key.toString(),
                            "",
                            -1,
                            "",
                            ""
                        )
                    )
                    val ct = courseType.key.toString()
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

    private fun toClassItem(childItem: DataSnapshot): ClassItem {
        val name = childItem.child("name").value.toString()
        val short = childItem.child("short").value.toString()
        val credits = childItem.child("credits").value.toString().toInt()
        val lastUpdated = childItem.child("lastUpdated").value.toString()
        val megaLink = childItem.child("megaLink").value.toString()

        return ClassItem(name, short, credits, lastUpdated, megaLink)
    }

    override fun onItemClicked(item: Data) {
        val it = item as ClassItem
        if(it.megaLink.length > 5) startActivity( Intent(Intent.ACTION_VIEW, Uri.parse(it.megaLink)) )
    }

}
