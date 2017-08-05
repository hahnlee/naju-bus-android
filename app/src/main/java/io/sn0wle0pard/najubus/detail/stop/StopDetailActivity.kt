package io.sn0wle0pard.najubus.detail.stop

import android.os.Bundle

import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.View

import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

import kotlinx.android.synthetic.main.activity_stop_detail.*

import io.sn0wle0pard.najubus.R
import io.sn0wle0pard.najubus.detail.stop.network.BusArriveAdapter
import io.sn0wle0pard.najubus.detail.stop.network.BusArriveInfo

class StopDetailActivity : AppCompatActivity(), OnMapReadyCallback {
    private var mBusStopID: Int = 0
    private var mBusStopName: String = ""
    private var latitude: Double = 0.0
    private var longitude: Double = 0.0
    val presenter = StopDetailPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stop_detail)

        // Set Map Fragment
        val mapFragment: SupportMapFragment = map as SupportMapFragment
        mapFragment.getMapAsync(this)

        // Get Data from intent
        val intent = intent
        mBusStopID = intent.getIntExtra("busStopID", 0)
        mBusStopName = intent.getStringExtra("busStopName")
        latitude = intent.getDoubleExtra("latitude", 0.0)
        longitude = intent.getDoubleExtra("longitude", 0.0)
        val busStopDir = intent.getStringExtra("busStopDir")
        val toolbar: Toolbar = toolbar
        toolbar.title = mBusStopName
        toolbar.subtitle = busStopDir

        val mLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)

        bus_arrive_list.layoutManager = mLayoutManager
        bus_arrive_list.setHasFixedSize(true)

        val adView: AdView = adView
        val adRequest: AdRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)

        onLoad()
    }

    override fun onResume() {
        super.onResume()
        presenter.loadBusArriveInfo(mBusStopID)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        val busStop = LatLng(latitude, longitude)
        googleMap.addMarker(MarkerOptions().position(busStop)
                .title(mBusStopName))
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(busStop, 18f))
    }

    fun onLoad() {
        bus_arrive_list.visibility = View.GONE
        load_list.visibility = View.VISIBLE
        no_arrive_bus.visibility = View.GONE
    }

    fun onEmpty() {
        bus_arrive_list.visibility = View.GONE
        load_list.visibility = View.GONE
        no_arrive_bus.visibility = View.VISIBLE
    }

    fun onComplete() {
        bus_arrive_list.visibility = View.VISIBLE
        load_list.visibility = View.GONE
        no_arrive_bus.visibility = View.GONE
    }

    fun setRecyclerView(busArriveInfoList: List<BusArriveInfo>) {
        val adapter: BusArriveAdapter = BusArriveAdapter(busArriveInfoList)
        bus_arrive_list.adapter = adapter
        onComplete()
    }
}