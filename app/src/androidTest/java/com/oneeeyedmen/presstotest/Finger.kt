package com.oneeeyedmen.presstotest

import android.view.MotionEvent
import android.view.View
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.GeneralLocation
import androidx.test.espresso.action.MotionEvents
import androidx.test.espresso.action.Press
import androidx.test.espresso.matcher.ViewMatchers
import org.hamcrest.Matcher

// https://stackoverflow.com/questions/32010927/android-espresso-make-assertion-while-button-is-kept-pressed
object Finger {
    internal var sMotionEventDownHeldView: MotionEvent? = null

    fun pressAndHold(): ViewAction {
        return PressAndHoldAction()
    }

    fun release(): ViewAction {
        return ReleaseAction()
    }

    fun tearDown() {
        sMotionEventDownHeldView = null
    }

    internal class PressAndHoldAction : ViewAction {
        override fun getConstraints(): Matcher<View> {
            return ViewMatchers.isDisplayingAtLeast(90) // Like GeneralClickAction
        }

        override fun getDescription(): String {
            return "Press and hold action"
        }

        override fun perform(uiController: UiController, view: View) {
            if (sMotionEventDownHeldView != null) {
                throw AssertionError("Only one view can be held at a time")
            }

            val precision = Press.FINGER.describePrecision()
            val coords = GeneralLocation.CENTER.calculateCoordinates(view)
            sMotionEventDownHeldView = MotionEvents.sendDown(
                uiController,
                coords,
                precision
            ).down
            // TODO: save view information and make sure release() is on same view
        }
    }

    internal class ReleaseAction : ViewAction {
        override fun getConstraints(): Matcher<View> {
            return ViewMatchers.isDisplayingAtLeast(90)  // Like GeneralClickAction
        }

        override fun getDescription(): String {
            return "Release action"
        }

        override fun perform(uiController: UiController, view: View) {
            if (sMotionEventDownHeldView == null) {
                throw AssertionError("Before calling release(), you must call pressAndHold() on a view")
            }

            val coords = GeneralLocation.CENTER.calculateCoordinates(view)
            MotionEvents.sendUp(uiController, sMotionEventDownHeldView!!, coords)
        }
    }
}