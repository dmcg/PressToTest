package com.oneeyedmen.presstotest

import android.view.MotionEvent
import android.view.View
import kotlin.properties.Delegates

class ViewModel(
    private val onButtonTextChanged: (String) -> Unit,
    private var goBoom: () -> Unit
) {

    private val defaultText = "Press to Test"
    private val pressedText = "Release to Detonate"

    var buttonText: String by Delegates.observable(defaultText) { _, _, newValue ->
        onButtonTextChanged(newValue)
    }

    init {
        // required to sync the view on creation
        buttonText = defaultText
    }

    fun onTouchAction(actionCode:Int) {
        when (actionCode) {
            MotionEvent.ACTION_DOWN -> buttonText = pressedText
            MotionEvent.ACTION_UP -> buttonText = defaultText
        }
    }

    fun onClick() {
        goBoom()
    }

    val onTouchListener = View.OnTouchListener { _, event ->
        onTouchAction(event.actionMasked)
        false
    }

    val onClickListener = View.OnClickListener { onClick() }
}