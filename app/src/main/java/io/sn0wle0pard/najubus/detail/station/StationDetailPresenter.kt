package io.sn0wle0pard.najubus.detail.station

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.realm.Realm
import io.realm.RealmResults

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

import io.sn0wle0pard.najubus.detail.station.network.BusArriveRequest
import io.sn0wle0pard.najubus.model.StaredStation
import kotlin.properties.Delegates

class StationDetailPresenter(val view: StationDetailActivity) {
    private val mCompositeDisposable: CompositeDisposable = CompositeDisposable()
    private val observer: Observable<Int> = Observable.just(0)
    private var realm: Realm by Delegates.notNull()
    private var isStared = false

    init {
        Realm.init(view.applicationContext)
        realm = Realm.getDefaultInstance()
    }

    val busArriveRequest: BusArriveRequest = Retrofit.Builder()
            .baseUrl("http://bis.naju.go.kr")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create<BusArriveRequest>(BusArriveRequest::class.java)

    fun onCreate(lineID: Int) {
        val started: RealmResults<StaredStation> = realm.where(StaredStation::class.java)
                .equalTo("lineID", lineID).findAll()
        isStared = started.size != 0
        if (isStared) {
            view.setStared()
        } else {
            view.setUnStared()
        }
    }

    fun loadBusArriveInfo(busStopID: Int) {
        view.onLoad()
        observer.subscribe()
        mCompositeDisposable.add(busArriveRequest.getBusArriveInfo(busStopID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(
                {
                    // On Next
                    busArriveInfoList ->
                        if (busArriveInfoList.list.isEmpty()) {
                            view.onEmpty()
                        } else {
                            view.setRecyclerView(busArriveInfoList.list)
                        }
                },
                {
                    // On Error
                })
        )
    }

    /**
     * click fab button save or delete db
     * */
    fun clickFabButton(lineID: Int, lineName: String, lineDir: String, latitude: Double, longitude: Double) {
        realm.beginTransaction()
        if (isStared) {
            val startedStation: RealmResults<StaredStation> = realm.where(StaredStation::class.java)
                    .equalTo("lineID", lineID).findAll()
            startedStation.deleteAllFromRealm()
            realm.commitTransaction()
            isStared = false
            view.setUnStared()
            view.showSnackBar("즐겨찾기에서 제거 되었습니다")
        } else {
            val startStation: StaredStation = realm.createObject(StaredStation::class.java)
            startStation.lineID = lineID
            startStation.lineName = lineName
            startStation.lineDir = lineDir
            startStation.latitude = latitude
            startStation.longitude = longitude
            realm.commitTransaction()
            isStared = true
            view.setStared()
            view.showSnackBar("즐겨찾기에 추가 되었습니다")
        }
    }
}