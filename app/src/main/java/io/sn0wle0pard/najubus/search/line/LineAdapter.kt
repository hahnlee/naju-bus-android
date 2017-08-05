package io.sn0wle0pard.najubus.search.station

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import io.sn0wle0pard.najubus.R
import io.sn0wle0pard.najubus.search.line.LineInfo

class LineAdapter(val mSearchList: List<LineInfo>) :
        RecyclerView.Adapter<LineAdapter.ViewHolder>() {
    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val searchResultTitle: TextView = v.findViewById(R.id.search_result_title) as TextView
        val searchResultSubtitle: TextView = v.findViewById(R.id.search_result_subtitle) as TextView
        val searchResultId: TextView = v.findViewById(R.id.search_result_id) as TextView
        val searchAvatar: ImageView = v.findViewById(R.id.search_avatar) as ImageView
    }

    override fun getItemCount(): Int {
        return mSearchList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val searchResult = mSearchList[position]
        holder.searchResultTitle.text = searchResult.getLineName()
        holder.searchResultId.text = searchResult.lineID.toString()
        holder.searchResultSubtitle.text = searchResult.getLineInfo()
        holder.searchAvatar.setImageResource(R.drawable.ic_directions_bus_black_24dp)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.search_result_item, parent, false)
        return ViewHolder(v)
    }
}