package io.sn0wle0pard.najubus.search.station

import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface StationRequest {
    @FormUrlEncoded
    @POST("/busmap/stationInfo.do")
    fun searchBusStation(@Field("searchBusStopName") name: String): Observable<StationList>
}
