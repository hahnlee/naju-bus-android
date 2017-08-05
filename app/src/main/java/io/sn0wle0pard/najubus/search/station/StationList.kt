package io.sn0wle0pard.najubus.search.station

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class StationList {
    @SerializedName("list")
    @Expose
    var list: List<StationInfo> = ArrayList()

    @SerializedName("size")
    @Expose
    var size: Int = 0
}