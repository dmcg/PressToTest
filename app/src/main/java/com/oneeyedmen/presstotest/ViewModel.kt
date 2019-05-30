package com.oneeyedmen.presstotest

import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.MutableLiveData


class ViewModel(
    private val defaultText: String,
    private val pressedText: String,
    private var goBoom: () -> Unit
) {
    var buttonText = MutableLiveData<String>(defaultText)

    val onTouchListener = OnTouchListener { _, event ->
        onTouchAction(event.action)
        false
    }

    val onClickListener = View.OnClickListener { onClick() }

    @VisibleForTesting
    internal fun onTouchAction(actionCode: Int) {
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