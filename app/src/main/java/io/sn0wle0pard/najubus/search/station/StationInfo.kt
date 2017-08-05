package io.sn0wle0pard.najubus.search.station

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class StationInfo {
    @SerializedName("BUSSTOP_NAME")
    @Expose
    var busStopName: String = ""

    @SerializedName("ARS_ID")
    @Expose
    var arsID: Int = 0

    @SerializedName("NEXT_BUSSTOP")
    @Expose
    var nextBusStop: String? = null

    @SerializedName("BUSSTOP_ID")
    @Expose
    var busStopID: Int = 0

    @SerializedName("LONGITUDE")
    @Expose
    var longitude: Double = 0.0

    @SerializedName("BIT_FLAG")
    @Expose
    var bitFlag: String? = null

    @SerializedName("LATITUDE")
    @Expose
    var latitude: Double = 0.0
}
