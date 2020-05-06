package com.frozenbrain.fiimateriale.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.frozenbrain.fiimateriale.R
import kotlinx.android.synthetic.main.fragment_free_rooms.*
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import org.jsoup.Connection
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.io.IOException
import java.lang.Exception
import java.net.SocketTimeoutException

class FreeRoomsFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_free_rooms, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        CoroutineScope(IO).launch {
            fetch();
        }

    }

    suspend fun fetch() {
        try {
            val doc: Document = Jsoup.connect("https://profs.info.uaic.ro/~orar/globale/sali_libere.html").get()
            var name = ""
            for (el in doc.select("tr")) {
                for (tr in el.select("td")) {
                    val title = tr.select("font").text()
                    name += (title + " ");
                }
            }

            withContext(Main) {
                fetchHTML(name)
            }

        } catch (e: Exception) {

        }
    }

    private fun fetchHTML(html: String) {
        helloText.text = html
    }

}
