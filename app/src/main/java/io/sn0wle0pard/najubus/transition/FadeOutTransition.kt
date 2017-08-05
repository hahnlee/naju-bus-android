package io.sn0wle0pard.najubus.transition

import android.support.transition.AutoTransition
import android.support.transition.Transition

class FadeOutTransition: AutoTransition() {
    val FADE_OUT_DURATION: Long = 250

    fun withAction(finishingAction: TransitionListener): Transition {
        val transition: AutoTransition = AutoTransition()
        transition.duration = FADE_OUT_DURATION
        transition.addListener(finishingAction)
        return transition
    }
}