package io.sn0wle0pard.najubus.search.stop

import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface StopRequest {
    @FormUrlEncoded
    @POST("/busmap/stationInfo.do")
    fun searchBusStop(@Field("searchBusStopName") name: String): Observable<StopList>
}
