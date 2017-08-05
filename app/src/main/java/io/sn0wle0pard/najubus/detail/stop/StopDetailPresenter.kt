package io.sn0wle0pard.najubus.detail.stop

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

import io.sn0wle0pard.najubus.detail.stop.network.BusArriveRequest

class StopDetailPresenter(val view: StopDetailActivity) {
    private val mCompositeDisposable: CompositeDisposable = CompositeDisposable()
    private val observer: Observable<Int> = Observable.just(0)

    val busArriveRequest: BusArriveRequest = Retrofit.Builder()
            .baseUrl("http://bis.naju.go.kr")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create<BusArriveRequest>(BusArriveRequest::class.java)


    fun loadBusArriveInfo(busStopID: Int) {
        view.onLoad()
        observer.subscribe()
        mCompositeDisposable.add(busArriveRequest.getBusArriveInfo(busStopID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(
                {
                    busArriveInfoList ->
                        if (busArriveInfoList.list.isEmpty()) {
                            view.onEmpty()
                        } else {
                            view.setRecyclerView(busArriveInfoList.list)
                        }
                },
                {
                })
        )
    }
}