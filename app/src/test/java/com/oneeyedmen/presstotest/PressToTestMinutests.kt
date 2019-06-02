package com.oneeyedmen.presstotest

import android.view.MotionEvent.ACTION_DOWN
import android.view.MotionEvent.ACTION_UP
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import dev.minutest.junit.experimental.JUnit4Minutests
import dev.minutest.junit.experimental.applyRule
import dev.minutest.rootContext
import org.junit.Assert.assertEquals

class PressToTestMinutests : JUnit4Minutests() {

    class Fixture {
        val rule = InstantTaskExecutorRule()

        var boomCount = 0

        val viewModel = ViewModel(
            defaultText = "Press to Test",
            pressedText = "Release to Detonate",
            goBoom = { boomCount++ }
        )

        val buttonText get() = viewModel.buttonText.value

        fun click() = viewModel.onClick()

        fun touch(actionCode: Int) = viewModel.onTouchAction(actionCode)
    }

    fun tests() = rootContext<Fixture> {

        fixture { Fixture() }

        applyRule(Fixture::rule)

        test("button message changes on pressing") {
            assertEquals("Press to Test", buttonText)

            touch(ACTION_DOWN)
            assertEquals("Release to Detonate", buttonText)

            touch(ACTION_UP)
            assertEquals("Press to Test", buttonText)
        }

        test("clicking button sets off the explosion") {
            assertEquals(0, boomCount)

            click()
            assertEquals(1, boomCount)

            click()
            assertEquals(2, boomCount)
        }
    }
}