package com.oneeeyedmen.presstotest

import android.view.View
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.hamcrest.Matcher
import org.hamcrest.core.AllOf.allOf
import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Rule
import androidx.test.espresso.action.MotionEvents
import androidx.test.espresso.action.GeneralLocation
import androidx.test.espresso.matcher.ViewMatchers.isDisplayingAtLeast
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.Press
import android.view.MotionEvent
import androidx.test.espresso.UiController


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun button_message_changes_on_pressing() {
        onView(buttonMatcher).check(matches(
            allOf(
                isDisplayed(),
                withText("PRESS TO TEST")
            )))

        onView(buttonMatcher).perform(Finger.pressAndHold())
        onView(buttonMatcher).check(matches(
            allOf(
                isDisplayed(),
                withText("RELEASE TO DETONATE")
            )))

        onView(buttonMatcher).perform(Finger.release())
        onView(buttonMatcher).check(matches(
            allOf(
                isDisplayed(),
                withText("PRESS TO TEST")
            )))
    }

    @Test
    fun clicking_button_shows_temporary_BOOM_message() {

        onView(snackBarMatcher).check(doesNotExist())

        onView(buttonMatcher).perform(click())
        onView(snackBarMatcher).check(matches(isDisplayed()))

        Thread.sleep(3000)
        onView(snackBarMatcher).check(doesNotExist())
    }
}

private val buttonMatcher = withId(R.id.button)
private val snackBarMatcher = allOf(
    withId(android.support.design.R.id.snackbar_text),
    withText("BOOM!")
)

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
            return isDisplayingAtLeast(90) // Like GeneralClickAction
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
            sMotionEventDownHeldView = MotionEvents.sendDown(uiController, coords, precision).down
            // TODO: save view information and make sure release() is on same view
        }
    }

    internal class ReleaseAction : ViewAction {
        override fun getConstraints(): Matcher<View> {
            return isDisplayingAtLeast(90)  // Like GeneralClickAction
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