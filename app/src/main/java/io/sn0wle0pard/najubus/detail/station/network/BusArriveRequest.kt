package io.sn0wle0pard.najubus.detail.station.network

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface BusArriveRequest {
    @GET("/busmap/lineStationArriveInfoList.do")
    fun getBusArriveInfo(@Query("BUSSTOP_ID") busStopID: Int): Observable<BusArriveInfoList>
}