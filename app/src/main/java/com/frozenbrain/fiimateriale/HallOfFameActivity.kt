package com.frozenbrain.fiimateriale

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_feedback.*
import kotlinx.android.synthetic.main.activity_feedback.toolbar_feedback
import kotlinx.android.synthetic.main.activity_hall_of_fame.*

class HallOfFameActivity : AppCompatActivity() {

    private lateinit var db: DatabaseReference
    private lateinit var list: MutableList<Person>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hall_of_fame)
        toolbar_feedback.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp)
        toolbar_feedback.setNavigationOnClickListener{
            finish()
        }

        db = FirebaseDatabase.getInstance().reference.child("Strings/Hall Of Fame")
        list = mutableListOf()

        hof_scrollBar.visibility = View.GONE

        getFromDatabase()
    }

    private fun getFromDatabase() {

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                for (childItem in dataSnapshot.children) {
                    val item = toPersonItem(childItem)
                    list.add(item)
                }
                onDataInit(list)
            }
            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(baseContext, "Failed to load people.", Toast.LENGTH_SHORT).show()
            }
        }
        db.addValueEventListener(postListener)
    }

    private fun onDataInit(list: MutableList<Person>) {
        // TODO, :::: Recycler View
        Glide.with(this).load(list[0].imageLink).into(hof_legendImage)
        hof_legendName.text = list[0].name
        hof_legendBody.text = list[0].body
        hof_legendRole.text = list[0].role
        hof_legendLink.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(list[0].link)))
        }

        Glide.with(this).load(list[1].imageLink).into(hof_valentinImage)
        hof_valentinName.text = list[1].name
        hof_valentinBody.text = list[1].body
        hof_valentinRole.text = list[1].role
        hof_valentinLink.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(list[1].link)))
        }

        Glide.with(this).load(list[2].imageLink).into(hof_madalinaImage)
        hof_madalinaName.text = list[2].name
        hof_madalinaBody.text = list[2].body
        hof_madalinaRole.text = list[2].role
        hof_madalinaLink.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(list[2].link)))
        }

        hof_progressBar.visibility = View.GONE
        hof_scrollBar.visibility = View.VISIBLE

    }

    private fun toPersonItem(snapshot: DataSnapshot): HallOfFameActivity.Person {
        val name = snapshot.child("title").value.toString()
        val body = snapshot.child("body").value.toString()
        val role = "Role: " + snapshot.child("role").value.toString()
        val imageLink = snapshot.child("avatarLink").value.toString()
        val link = snapshot.child("link").value.toString()
        return Person(name, body, role, imageLink, link)
    }

    data class Person(val name: String, val body: String, val role: String, val imageLink: String, val link: String)

}
