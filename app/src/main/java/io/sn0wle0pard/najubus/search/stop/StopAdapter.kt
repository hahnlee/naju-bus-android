package io.sn0wle0pard.najubus.search.stop

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import io.sn0wle0pard.najubus.R
import io.sn0wle0pard.najubus.detail.stop.StopDetailActivity

class StopAdapter(val mSearchList: List<StopInfo>) :
        RecyclerView.Adapter<StopAdapter.ViewHolder>() {
    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val searchResultTitle: TextView = v.findViewById(R.id.search_result_title) as TextView
        val searchResultSubtitle: TextView = v.findViewById(R.id.search_result_subtitle) as TextView
        val searchResultId: TextView = v.findViewById(R.id.search_result_id) as TextView
        val searchAvatar: ImageView = v.findViewById(R.id.search_avatar) as ImageView

        /**
         * Set Recycler View Item Click Listener
         * */
        fun setClick(busStopID: Int, busStopName: String, busStopDir: String,
                     latitude: Double, longitude: Double) {
            itemView.setOnClickListener {
                val context = itemView.context
                val intent = Intent(context, StopDetailActivity::class.java)
                intent.putExtra("busStopID", busStopID)
                intent.putExtra("busStopName", busStopName)
                intent.putExtra("busStopDir", busStopDir)
                intent.putExtra("latitude", latitude)
                intent.putExtra("longitude", longitude)
                context.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int {
        return mSearchList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val searchResult = mSearchList[position]
        holder.searchResultTitle.text = searchResult.busStopName
        holder.searchResultId.text = searchResult.busStopID.toString()
        holder.searchAvatar.setImageResource(R.drawable.ic_place_black_24dp)
        var busStopDir = ""
        if (searchResult.nextBusStop != null) {
            busStopDir = "${searchResult.nextBusStop} 방향"
        }
        holder.searchResultSubtitle.text = busStopDir
        holder.setClick(searchResult.busStopID, searchResult.busStopName, busStopDir,
                searchResult.latitude, searchResult.longitude)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.search_result_item, parent, false)
        return ViewHolder(v)
    }
}