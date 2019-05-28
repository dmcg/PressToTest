package com.oneeyedmen.presstotest

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import kotlinx.android.synthetic.main.activity_main.*
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PressToTestTests {

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)


    val buttonText: String get() = activityRule.activity.button.text.toString()

    @Test
    fun `button message changes on pressing`() {
        assertEquals("Press to Test", buttonText)

//        button.touchButton()
//        assertEquals("Release to Detonate", buttonText)

//        untouchButton()
//        assertEquals("Press to Test", buttonText)
    }
}
