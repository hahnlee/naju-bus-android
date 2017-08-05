package io.sn0wle0pard.najubus.search

import android.os.Bundle

import android.support.design.widget.TabLayout
import android.support.transition.TransitionManager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar

import android.text.Editable
import android.text.TextWatcher

import android.view.View
import android.view.ViewTreeObserver

import io.sn0wle0pard.najubus.R
import io.sn0wle0pard.najubus.search.line.LineInfo
import io.sn0wle0pard.najubus.search.station.LineAdapter
import io.sn0wle0pard.najubus.search.station.StationAdapter
import io.sn0wle0pard.najubus.search.station.StationInfo
import io.sn0wle0pard.najubus.transition.FadeInTransition

import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity() {
    val presenter: SearchPresenter = SearchPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        setSupportActionBar(toolbar as Toolbar)
        // display empty text
        showEmptyText()
        // Show Home button
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        // Set to Home button (when false it will show hamburger button)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        // First load
        if (savedInstanceState == null) {
            val viewTreeObserver: ViewTreeObserver = toolbar.viewTreeObserver
            viewTreeObserver.addOnGlobalLayoutListener {
                TransitionManager.beginDelayedTransition(search_bar_extend, FadeInTransition().createTransition())
            }
        }

        // Set tab select
        search_bar_tab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab) {
            }

            override fun onTabSelected(tab: TabLayout.Tab) {
                presenter.setSearchMode(tab.position)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
            }
        })

        val mLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)

        // Set search bar action
        search_result.layoutManager = mLayoutManager
        search_result.setHasFixedSize(true)
        search_view.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(p0: CharSequence, p1: Int, p2: Int, p3: Int) {
                if (p0.isNotEmpty()) {
                    presenter.searchBusInfo(p0.toString())
                } else {
                    showEmptyText()
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
    }

    /**
     * Set Home button (not hamburger button)
     * */
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    /**
     * Show Search text is empty on screen and clear text
     * */
    fun showEmptyText() {
        search_view.text.clear()
        emptyText.visibility = View.VISIBLE
        loadSearchResult.visibility = View.GONE
        search_result.visibility = View.GONE
        emptyResult.visibility = View.GONE
    }

    /**
     * Show search result is empty on screen
     * */
    fun showEmpty() {
        emptyText.visibility = View.GONE
        loadSearchResult.visibility = View.GONE
        search_result.visibility = View.GONE
        emptyResult.visibility = View.VISIBLE
    }

    /**
     * Show progressbar on screen and other element hide
     * */
    fun showLoad() {
        emptyText.visibility = View.GONE
        loadSearchResult.visibility = View.VISIBLE
        search_result.visibility = View.GONE
        emptyResult.visibility = View.GONE
    }

    /**
     * Show network error message on screen
     * */
    fun showError() {
    }


    fun setStopRecyclerView(searchResult: List<StationInfo>) {
        val adapter: StationAdapter = StationAdapter(searchResult)
        search_result.adapter = adapter
        emptyText.visibility = View.GONE
        loadSearchResult.visibility = View.GONE
        search_result.visibility = View.VISIBLE
        emptyResult.visibility = View.GONE
    }

    fun setLineRecyclerView(searchResult: List<LineInfo>) {
        val adapter: LineAdapter = LineAdapter(searchResult)
        search_result.adapter = adapter
        emptyText.visibility = View.GONE
        loadSearchResult.visibility = View.GONE
        search_result.visibility = View.VISIBLE
        emptyResult.visibility = View.GONE
    }


    override fun finish() {
        super.finish()
        presenter.unSubScribe()
        this.overridePendingTransition(0, 0)
    }
}
