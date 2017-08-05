package io.sn0wle0pard.najubus.search.line

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class LineInfo {

    @SerializedName("LINENAME")
    @Expose
    private var lineName: String = ""

    @SerializedName("LINEID")
    @Expose
    var lineID: Int = 0

    fun getLineName(): String {
        return lineName!!.split("\\(".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()[0]
    }

    fun getLineInfo(): String {
        return lineName!!.split("[\\(\\)]".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()[1]
    }
}
