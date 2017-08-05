package io.sn0wle0pard.najubus.transition

import android.support.transition.AutoTransition
import android.support.transition.Transition

class FadeInTransition: AutoTransition() {
    val FADE_IN_DURATION: Long = 200

    fun createTransition(): Transition {
        val transition: AutoTransition = AutoTransition()
        transition.duration = FADE_IN_DURATION
        return transition
    }
}
