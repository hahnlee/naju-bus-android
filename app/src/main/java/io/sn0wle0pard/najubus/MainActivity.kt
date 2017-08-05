package io.sn0wle0pard.najubus

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle

import android.support.v7.app.AppCompatActivity
import android.support.transition.Fade
import android.support.transition.Transition
import android.support.transition.Transition.TransitionListener
import android.support.transition.TransitionManager

import android.view.inputmethod.InputMethodManager
import android.view.ViewGroup

import io.sn0wle0pard.najubus.search.SearchActivity
import io.sn0wle0pard.najubus.transition.FadeInTransition
import io.sn0wle0pard.najubus.transition.FadeOutTransition

import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    var toolBarMargin: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Convert dp to px
        toolBarMargin = resources.getDimensionPixelOffset(R.dimen.toolbar_margin)
        search_bar.setOnClickListener {
            showKeyboard()
            transitionToSearch()
        }
    }

    override fun onResume() {
        super.onResume()
        TransitionManager.beginDelayedTransition(toolbar, FadeInTransition().createTransition())
        val layoutParam: ViewGroup.MarginLayoutParams = search_bar.layoutParams as ViewGroup.MarginLayoutParams
        layoutParam.setMargins(toolBarMargin, toolBarMargin, toolBarMargin, toolBarMargin)
        search_bar.layoutParams = layoutParam
    }

    private fun hideKeyboard() {
        val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)
    }

    private fun showKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0)
    }

    fun transitionToSearch() {
        val transition = FadeOutTransition().withAction(navigateToSearch())
        TransitionManager.beginDelayedTransition(search_bar, transition)
        TransitionManager.beginDelayedTransition(container, Fade(Fade.OUT))
        container.visibility = ViewGroup.GONE
        val layoutParam: ViewGroup.MarginLayoutParams = search_bar.layoutParams as ViewGroup.MarginLayoutParams
        layoutParam.setMargins(0, 0, 0, 0)
        search_bar.layoutParams = layoutParam

    }

    fun navigateToSearch(): TransitionListener {
        val intent: Intent = Intent(this, SearchActivity::class.java)
            return object :TransitionListener {
                override fun onTransitionEnd(transition: Transition) {
                    startActivity(intent)
                    overridePendingTransition(0, 0)
                }

                override fun onTransitionCancel(transition: Transition) {
                }

                override fun onTransitionPause(transition: Transition) {
                }

                override fun onTransitionResume(transition: Transition) {
                }

                override fun onTransitionStart(transition: Transition) {
                }
            }
    }

}
