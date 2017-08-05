package io.sn0wle0pard.najubus.detail.stop.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class BusArriveInfoList {

    @SerializedName("list")
    @Expose
    var list: List<BusArriveInfo> = ArrayList<BusArriveInfo>()

    @SerializedName("size")
    @Expose
    var size: Int = 0
}