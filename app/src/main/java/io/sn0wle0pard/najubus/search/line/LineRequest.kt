package io.sn0wle0pard.najubus.search.line

import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface LineRequest {
    @FormUrlEncoded
    @POST("/busmap/lineInfo.do")
    fun searchBusLine(@Field("searchLineName") name: String): Observable<LineList>
}