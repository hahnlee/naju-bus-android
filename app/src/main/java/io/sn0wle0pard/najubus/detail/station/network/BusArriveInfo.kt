package io.sn0wle0pard.najubus.detail.station.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class BusArriveInfo {
    @SerializedName("REMAIN_MIN")
    @Expose
    var remainMin: Int = 0

    @SerializedName("ENG_BUSSTOP_NAME")
    @Expose
    var engBusStopName: String? = null

    @SerializedName("REMAIN_STOP")
    @Expose
    var remainStop: Int = 0

    @SerializedName("BUSSTOP_NAME")
    @Expose
    var busStopName: String? = null

    @SerializedName("LINE_NAME")
    @Expose
    var lineName: String? = null
}
