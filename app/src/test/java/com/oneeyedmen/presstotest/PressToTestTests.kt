package com.oneeyedmen.presstotest

import android.view.MotionEvent
import org.junit.Assert.assertEquals
import org.junit.Test

class PressToTestTests {

    var buttonText = "DEFAULT"
    var boomCount = 0

    val viewModel = ViewModel(
        defaultText = "Press to Test",
        pressedText = "Release to Detonate",
        onButtonTextChanged = { buttonText = it },
        goBoom = { boomCount++ }
    )

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