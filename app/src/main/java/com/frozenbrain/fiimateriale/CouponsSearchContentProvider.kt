package com.frozenbrain.fiimateriale

import android.app.SearchManager
import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.database.MatrixCursor
import android.net.Uri
import android.widget.Toast
import androidx.annotation.Nullable


class CouponsSearchContentProvider : ContentProvider() {
    companion object {
        private const val STORES = "stores/" + SearchManager.SUGGEST_URI_PATH_QUERY + "/*"
        private val uriMatcher = UriMatcher(UriMatcher.NO_MATCH)
        private val matrixCursorColumns = arrayOf(
            "_id",
            SearchManager.SUGGEST_COLUMN_TEXT_1,
            SearchManager.SUGGEST_COLUMN_ICON_1,
            SearchManager.SUGGEST_COLUMN_INTENT_DATA
        )

        init {
            uriMatcher.addURI(
                "com.frozenbrain.fiimateriale.search",
                STORES,
                1
            )
        }
    }

    override fun onCreate(): Boolean {
        return true
    }

    @Nullable
    override fun query(
        uri: Uri,
        projection: Array<String>?,
        selection: String?,
        selectionArgs: Array<String>?,
        sortOrder: String?
    ): Cursor? {
        val queryType = ""
        return when (uriMatcher.match(uri)) {
            1 -> {
                val query = uri.lastPathSegment!!.toLowerCase()
                getSearchResultsCursor(query)
            }
            else -> null
        }
    }

    private fun getSearchResultsCursor(searchString: String): MatrixCursor {

        var searchString: String? = searchString
        val searchResults =
            MatrixCursor(matrixCursorColumns)
        val mRow = arrayOfNulls<Any>(4)
        var counterId = 0

        val stores = StoresData

        if (searchString != null) {
            searchString = searchString.toLowerCase()

            val storeList: List<String>? = stores.getStores();
            if(storeList != null) {
                for (rec in storeList) {
                    if (rec.toLowerCase().contains(searchString)) {
                        mRow[0] = "" + counterId++
                        mRow[1] = rec
                        mRow[2] = context!!.resources.getIdentifier(
                            getStoreName(rec),
                            "drawable", context!!.packageName
                        )
                        mRow[3] = "" + counterId++
                        searchResults.addRow(mRow)
                    }
                }
            }
        }
        return searchResults
    }

    private fun getStoreName(suggestion: String): String {
        val suggestionWords = suggestion.split(" ").toTypedArray()
        return suggestionWords[0].toLowerCase()
    }

    @Nullable
    override fun getType(uri: Uri): String? {
        return null
    }

    @Nullable
    override fun insert(
        uri: Uri,
        @Nullable contentValues: ContentValues?
    ): Uri? {
        return null
    }

    override fun delete(
        uri: Uri,
        @Nullable s: String?,
        @Nullable strings: Array<String>?
    ): Int {
        return 0
    }

    override fun update(
        uri: Uri,
        @Nullable contentValues: ContentValues?,
        @Nullable s: String?,
        @Nullable strings: Array<String>?
    ): Int {
        return 0
    }
}