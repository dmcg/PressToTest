package com.oneeyedmen.presstotest

import android.view.MotionEvent
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class PressToTestTests {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private var boomCount = 0

    val viewModel = ViewModel(
        defaultText = "Press to Test",
        pressedText = "Release to Detonate",
        goBoom = { boomCount++ }
    )

    private val buttonText get() = viewModel.buttonText.value

    @Test
    fun `button message changes on pressing`() {
        assertEquals("Press to Test", buttonText)

        viewModel.onTouchAction(MotionEvent.ACTION_DOWN)
        assertEquals("Release to Detonate", buttonText)

        viewModel.onTouchAction(MotionEvent.ACTION_UP)
        assertEquals("Press to Test", buttonText)
    }

    @Test
    fun `clicking button sets off the explosion`() {
        assertEquals(0, boomCount)

        viewModel.onClick()
        assertEquals(1, boomCount)

        viewModel.onClick()
        assertEquals(2, boomCount)
    }
}