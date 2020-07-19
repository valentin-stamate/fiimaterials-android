package com.frozenbrain.fiimateriale

import com.frozenbrain.fiimateriale.data.Data
import com.frozenbrain.fiimateriale.data.FreeRoom
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import java.text.SimpleDateFormat
import java.util.*

object FreeRoomsApi {
    private const val FREE_ROOM_TAG = "#66FF66"
    private const val SITE_LINK = "https://profs.info.uaic.ro/~orar/globale/sali_libere.html"
    private val map = mapOf<Int, String>(1 to "Monday", 2 to "Tuesday", 3 to "Wednesday", 4 to "Thursday", 5 to "Friday", 6 to "Saturday", 7 to "Sunday")

    suspend fun getFreeRooms(): List<Data> {
        val doc: Document = Jsoup.connect(SITE_LINK).get()
        val roomList: MutableList<Data> = mutableListOf()

        val date = Date()
        val hourFormat = SimpleDateFormat("HH:00", Locale.US)
        val dayFormat = SimpleDateFormat("EEEE", Locale.US)

        val currentDay = dayFormat.format(date)
        val currentHour = hourFormat.format(date)

        val bigTable = doc.select("tbody")
        for (classTable in bigTable.select("td[width=300px]")) {
            val className = classTable.select("font").text()
            var nr = 0;
            val classLines: MutableList<Element> = classTable.select("tr")
            classLines.removeAt(0)

            for (classLine in classLines) {

                val lineHour = classLine.select("td").first().text()

                val cellList: MutableList<Element> = classLine.select("td");
                cellList.removeAt(0)

                var nrDay = 1

                //name += (lineHour + " - ");
                for (cellItem in cellList) {
                    if (cellItem.outerHtml().contains("#66FF66")) {
                        //name += map[nrDay]
                        if (lineHour == currentHour && map[nrDay] == currentDay) {
                            roomList.add(FreeRoom(className, currentDay, lineHour))
                        }
                    }
                    nrDay++
                }


            }
        }

        return roomList;
    }

}