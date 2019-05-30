package com.oneeyedmen.presstotest

import android.view.MotionEvent
import android.widget.Button
import androidx.annotation.VisibleForTesting
import kotlin.properties.Delegates

class ViewModel(
    private val defaultText: String,
    private val pressedText: String,
    private val onButtonTextChanged: (String) -> Unit,
    private var goBoom: () -> Unit
) {
    private var buttonText: String by Delegates.observable(defaultText) { _, _, newValue ->
        onButtonTextChanged(newValue)
    }

    constructor(
        button: Button,
        defaultText: String,
        pressedText: String,
        goBoom: () -> Unit
    ) : this(
        defaultText = defaultText,
        pressedText = pressedText,
        onButtonTextChanged = button::setText,
        goBoom = goBoom
    ) {
        button.setOnTouchListener { _, event ->
            onTouchAction(event.actionMasked)
            false
        }
        button.setOnClickListener {
            onClick()
        }
    }

    init {
        // sync the view on creation
        buttonText = defaultText
    }

    @VisibleForTesting
    internal fun onTouchAction(actionCode:Int) {
        when (actionCode) {
            MotionEvent.ACTION_DOWN -> buttonText = pressedText
            MotionEvent.ACTION_UP -> buttonText = defaultText
        }
    }

    @VisibleForTesting
    internal fun onClick() {
        goBoom()
    }
}