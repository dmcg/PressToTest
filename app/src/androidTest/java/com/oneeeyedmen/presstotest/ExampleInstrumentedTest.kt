package com.oneeeyedmen.presstotest

import android.view.View
import androidx.test.InstrumentationRegistry
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

import org.junit.Assert.*
import org.junit.Rule
import java.lang.RuntimeException

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
    fun click_button() {
        val snackBarMatcher = allOf(
            withId(android.support.design.R.id.snackbar_text),
            withText("BOOM!")
        )

        onView(snackBarMatcher).check(doesNotExist())

        onView(withId(R.id.button)).perform(click())
        onView(snackBarMatcher).check(matches(isDisplayed()))

        Thread.sleep(3000)
        onView(snackBarMatcher).check(doesNotExist())
    }
}
