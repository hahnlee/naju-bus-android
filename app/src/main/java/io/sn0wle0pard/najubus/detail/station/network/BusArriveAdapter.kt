package io.sn0wle0pard.najubus.detail.station.network

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import io.sn0wle0pard.najubus.R

class BusArriveAdapter(val busArriveInfoList: List<BusArriveInfo>): RecyclerView.Adapter<BusArriveAdapter.ViewHolder>() {
    class ViewHolder(v: View): RecyclerView.ViewHolder(v) {
        val mLineName: TextView = v.findViewById(R.id.search_result_title) as TextView
        val mLineInfo: TextView = v.findViewById(R.id.search_result_subtitle) as TextView
        val mLineRemain: TextView = v.findViewById(R.id.search_result_id) as TextView
        val mAvatarView: ImageView = v.findViewById(R.id.search_avatar) as ImageView
    }

    override fun getItemCount(): Int {
        return busArriveInfoList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.search_result_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val busArriveInfo = busArriveInfoList[position]
        holder.mLineName.text = busArriveInfo.lineName
        holder.mLineInfo.text = "${busArriveInfo.busStopName} (${busArriveInfo.remainStop} 정류장 전)"
        holder.mLineRemain.text = "${busArriveInfo.remainMin}분 후 도착예정"
        holder.mAvatarView.setImageResource(R.drawable.ic_directions_bus_black_24dp)
    }

}