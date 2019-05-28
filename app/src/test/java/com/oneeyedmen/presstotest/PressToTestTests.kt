package com.oneeyedmen.presstotest

import org.junit.Assert.assertEquals
import org.junit.Test

class PressToTestTests {

    val viewModel = ViewModel()

    @Test
    fun `button message changes on pressing`() {
        assertEquals("Press to Test", viewModel.buttonText)

        viewModel.onButtonTouched()
        assertEquals("Release to Detonate", viewModel.buttonText)

        viewModel.onButtonUntouched()
        assertEquals("Press to Test", viewModel.buttonText)
    }
}

class ViewModel {

    private val defaultText = "Press to Test"
    private val pressedText = "Release to Detonate"

    var buttonText = defaultText

    fun onButtonTouched() {
        buttonText = pressedText
    }

    fun onButtonUntouched() {
        buttonText = defaultText
    }
}