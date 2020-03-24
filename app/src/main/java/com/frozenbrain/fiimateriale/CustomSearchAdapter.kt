import android.R
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class CustomSearchAdapter ( private val mContext: Context,
    private val searchResultItemLayout: Int,
    private val dataList: List<String>
) : ArrayAdapter<Any?>(mContext, searchResultItemLayout, dataList) {

    override fun getCount(): Int {
        return dataList.size
    }

    override fun getItem(position: Int): String? {
        return dataList[position]
    }

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        var view = view
        if (view == null) {
            view = LayoutInflater.from(parent.context)
                .inflate(searchResultItemLayout, parent, false)
        }
        val resultItem =
            view!!.findViewById<View>(R.id.text1) as TextView
        resultItem.text = getItem(position)
        return view
    }

}