package com.oneeyedmen.presstotest

import android.view.MotionEvent
import android.view.View
import kotlin.properties.Delegates

class ViewModel(
    private val defaultText: String = "Press to Test",
    private val pressedText: String = "Release to Detonate",
    private val onButtonTextChanged: (String) -> Unit,
    private var goBoom: () -> Unit
) {
    var buttonText: String by Delegates.observable(defaultText) { _, _, newValue ->
        onButtonTextChanged(newValue)
    }

    val onTouchListener = View.OnTouchListener { _, event ->
        onTouchAction(event.actionMasked)
        false
    }

    val onClickListener = View.OnClickListener { onClick() }

    init {
        // sync the view on creation
        buttonText = defaultText
    }

    internal fun onTouchAction(actionCode:Int) {
        when (actionCode) {
            MotionEvent.ACTION_DOWN -> buttonText = pressedText
            MotionEvent.ACTION_UP -> buttonText = defaultText
        }
    }

    internal fun onClick() {
        goBoom()
    }
}