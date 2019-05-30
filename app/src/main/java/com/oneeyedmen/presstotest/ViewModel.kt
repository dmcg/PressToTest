package com.oneeyedmen.presstotest

import android.view.MotionEvent
import android.widget.Button
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.MutableLiveData

class ViewModel(
    private val defaultText: String,
    private val pressedText: String,
    private var goBoom: () -> Unit
) {
    var buttonText = MutableLiveData<String>(defaultText)

    constructor(
        button: Button,
        defaultText: String,
        pressedText: String,
        goBoom: () -> Unit
    ) : this(
        defaultText = defaultText,
        pressedText = pressedText,
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

    @VisibleForTesting
    internal fun onTouchAction(actionCode:Int) {
        when (actionCode) {
            MotionEvent.ACTION_DOWN -> buttonText.value = pressedText
            MotionEvent.ACTION_UP -> buttonText.value = defaultText
        }
    }

    @VisibleForTesting
    internal fun onClick() {
        goBoom()
    }
}