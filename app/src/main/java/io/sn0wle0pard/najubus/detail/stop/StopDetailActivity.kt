package io.sn0wle0pard.najubus.detail.stop

import android.os.Bundle
import android.view.View

import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar

import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

import io.sn0wle0pard.najubus.R
import io.sn0wle0pard.najubus.detail.stop.network.BusArriveAdapter
import io.sn0wle0pard.najubus.detail.stop.network.BusArriveInfo

import kotlinx.android.synthetic.main.activity_stop_detail.*
import kotlin.properties.Delegates

class StopDetailActivity : AppCompatActivity(), OnMapReadyCallback {
    private var mBusStopID: Int = 0
    private var mBusStopName: String = ""
    private var latitude: Double = 0.0
    private var longitude: Double = 0.0
    private var mBusStopDir: String = ""
    private var presenter: StopDetailPresenter by Delegates.notNull()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stop_detail)
        // Set Map Fragment
        val mapFragment: SupportMapFragment = map as SupportMapFragment
        mapFragment.getMapAsync(this)
        presenter = StopDetailPresenter(this)

        // Get Data from intent
        val intent = intent
        mBusStopID = intent.getIntExtra("busStopID", 0)
        mBusStopName = intent.getStringExtra("busStopName")
        mBusStopDir = intent.getStringExtra("busStopDir")
        latitude = intent.getDoubleExtra("latitude", 0.0)
        longitude = intent.getDoubleExtra("longitude", 0.0)
        presenter.onCreate(mBusStopID)

        val toolbar: Toolbar = toolbar
        toolbar.title = mBusStopName
        toolbar.subtitle = mBusStopDir

        val mLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)

        bus_arrive_list.layoutManager = mLayoutManager
        bus_arrive_list.setHasFixedSize(true)

        val adView: AdView = adView
        val adRequest: AdRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)

        fabButton.setOnClickListener {
            presenter.clickFabButton(mBusStopID, mBusStopName, mBusStopDir, latitude, longitude)
        }

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

    fun setStared() {
        fabButton.setImageResource(R.drawable.ic_favorite_white_24dp)
    }

    fun setUnStared() {
        fabButton.setImageResource(R.drawable.ic_favorite_border_white_24dp)
    }

    fun showSnackBar(message: String) {
        Snackbar.make(container, message, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
    }
}