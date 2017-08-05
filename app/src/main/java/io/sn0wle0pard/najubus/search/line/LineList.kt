package io.sn0wle0pard.najubus.search.line

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

import java.util.ArrayList

class LineList {
    @SerializedName("list")
    @Expose
    var list: List<LineInfo> = ArrayList()
    @SerializedName("size")
    @Expose
    var size: Int = 0
}