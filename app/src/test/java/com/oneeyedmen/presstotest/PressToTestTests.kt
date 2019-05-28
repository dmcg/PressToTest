package com.oneeyedmen.presstotest

import android.view.MotionEvent
import android.view.View
import org.junit.Assert.assertEquals
import org.junit.Test

class PressToTestTests {

    val viewModel = ViewModel()

    @Test
    fun `button message changes on pressing`() {
        assertEquals("Press to Test", viewModel.buttonText)

        viewModel.onTouchListener.onTouch(null, motionEventWithAction(MotionEvent.ACTION_DOWN))
        assertEquals("Release to Detonate", viewModel.buttonText)

        viewModel.onTouchListener.onTouch(null, motionEventWithAction(MotionEvent.ACTION_UP))
        assertEquals("Press to Test", viewModel.buttonText)
    }

    private fun motionEventWithAction(action: Int) = MotionEvent.obtain(0, 0, action, 0.0F, 0.0F, 0)
}

class ViewModel {

    private val defaultText = "Press to Test"
    private val pressedText = "Release to Detonate"

    var buttonText = defaultText

    val onTouchListener = View.OnTouchListener { v, event ->
        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN -> buttonText = pressedText
            MotionEvent.ACTION_UP -> buttonText = defaultText
        }
        false
    }
}