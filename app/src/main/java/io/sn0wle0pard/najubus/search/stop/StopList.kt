package io.sn0wle0pard.najubus.search.stop

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class StopList {
    @SerializedName("list")
    @Expose
    var list: List<StopInfo> = ArrayList()

    @SerializedName("size")
    @Expose
    var size: Int = 0
}