package com.frozenbrain.fiimateriale

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_about.*

class AboutActivity : AppCompatActivity() {

    private lateinit var db: DatabaseReference
    private lateinit var ref: AboutActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        ref = this
        Glide.with(this).load("https://i.postimg.cc/VkKT4ZCw/20200225-175931.jpg").into(about_header_image)
        Glide.with(this).load("https://i.postimg.cc/gj455gT3/320200410-181509-me.jpg").into(about_MeImageView)
        about_scrollView.visibility = View.GONE

        db = FirebaseDatabase.getInstance().reference.child("Strings/About")

        fetchData()
    }

    private fun fetchData() {
        val contentList = mutableListOf<ContentItem>()

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                for (child in dataSnapshot.children) {
                    val contentItem = toContentItem(child)
                    contentList.add(contentItem)
                }
                onInitData(contentList)
            }
            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(ref, "Failed to load data.", Toast.LENGTH_SHORT).show()
            }
        }
        db.addValueEventListener(postListener)
    }

    private fun toContentItem(snapshot: DataSnapshot): ContentItem {
        val title = snapshot.child("title").value.toString()
        val body = snapshot.child("body").value.toString()
        return ContentItem(title, body)
    }

    private fun onInitData(list: MutableList<ContentItem>) {

        about_first_title.text = list[0].title
        about_first_body.text = list[0].body
        about_second_title.text = list[1].title
        about_second_body.text = list[1].body
        about_third_title.text = list[2].title
        about_third_body.text = list[2].body

        about_progress_bar.visibility = View.GONE
        about_scrollView.visibility = View.VISIBLE

    }

    data class ContentItem(val title: String, val body: String)

}
