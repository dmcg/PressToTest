package com.oneeyedmen.presstotest

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import junit.framework.AssertionFailedError
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.hamcrest.core.AllOf.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
abstract class AcceptanceTests(private val waiter: Waiter) {

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun button_message_changes_on_pressing() {
        val button = onView(buttonMatcher)

        button.check(isDisplayed(withText("PRESS TO TEST")))

        button.perform(Finger.pressAndHold())
        button.check(isDisplayed(withText("RELEASE TO DETONATE")))

        button.perform(Finger.release())
        button.check(isDisplayed(withText("PRESS TO TEST")))
    }

    @Test
    fun clicking_button_shows_temporary_BOOM_message() {

        onView(snackBarMatcher).check(doesNotExist())

        onView(buttonMatcher).perform(click())
        onView(snackBarMatcher).check(isDisplayed())

        waiter.waitForNo<AssertionFailedError>("Snackbar gone") {
            onView(snackBarMatcher).check(doesNotExist())
        }
    }
}


private val buttonMatcher = withId(R.id.button)

private val snackBarMatcher = allOf(
    withId(android.support.design.R.id.snackbar_text),
    withText("BOOM!")
)

private fun isDisplayed(matcher: Matcher<View> = Matchers.any(View::class.java)) = matches(
    allOf(
        ViewMatchers.isDisplayed(),
        matcher
    )
)

