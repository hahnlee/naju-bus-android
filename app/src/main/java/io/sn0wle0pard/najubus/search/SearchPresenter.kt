package io.sn0wle0pard.najubus.search

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.sn0wle0pard.najubus.search.line.LineRequest
import io.sn0wle0pard.najubus.search.stop.StopRequest
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * SearchActivity Presenter
 * @param view SearchActivity
 * */
class SearchPresenter(val view: SearchActivity) {
    var search: Int = 0

    // Build Retrofit
    val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("http://bis.naju.go.kr")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    val busStopRequest: StopRequest = retrofit.create(StopRequest::class.java)
    val busLineRequest: LineRequest = retrofit.create(LineRequest::class.java)

    val compositeSubscription: CompositeDisposable = CompositeDisposable()

    /**
     * Set search mode (0 is search bus stop, 1 is search bus line)
     * @param position Tab index
     * */
    fun setSearchMode(position: Int) {
        view.showEmptyText()
        search = position
    }

    /**
     * Search Bus Information via query and search mode
     * @param query bus line name or bus stop name
     * */
    fun searchBusInfo(query: String) {
        view.showLoad()
        if (search == 0) {
            compositeSubscription.add(
                    busStopRequest.searchBusStop(query)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe ({
                        // On Complete
                        // Show list only list is not empty
                        stopList ->
                        if (stopList.size>0) {
                            view.setStopRecyclerView(stopList.list)
                        }
                        else {
                            view.showEmpty()
                        }
                    }, {
                        // On Error
                        view.showEmpty()
                    })
            )
        } else {
            compositeSubscription.add(
                    busLineRequest.searchBusLine(query)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe ({
                        // On Complete
                        // Show list only list is not empty
                        lineList ->
                        if (lineList.size>0) {
                            view.setLineRecyclerView(lineList.list)
                        }
                        else {
                            view.showEmpty()
                        }
                    }, {
                        // On Error
                        view.showEmpty()
                    })
            )
        }
    }

    fun unSubScribe() {
        // Dispose Rx
        compositeSubscription.clear()
    }
}