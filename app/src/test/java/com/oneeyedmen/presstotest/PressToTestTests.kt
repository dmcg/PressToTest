package com.oneeyedmen.presstotest

import android.view.MotionEvent
import org.junit.Assert.assertEquals
import org.junit.Test

class PressToTestTests {

    var buttonText = "DEFAULT"
    val viewModel = ViewModel { buttonText = it }

    @Test
    fun `button message changes on pressing`() {
        assertEquals("Press to Test", buttonText)

        viewModel.onTouchAction(MotionEvent.ACTION_DOWN)
        assertEquals("Release to Detonate", buttonText)

        viewModel.onTouchAction(MotionEvent.ACTION_UP)
        assertEquals("Press to Test", buttonText)
    }
}