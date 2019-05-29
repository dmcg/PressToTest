package com.oneeyedmen.presstotest

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel = ViewModel(
            defaultText = getString(R.string.default_button_label),
            pressedText = getString(R.string.pressed_button_label),
            onButtonTextChanged = button::setText,
            goBoom = this::boom)
        button.setOnTouchListener(viewModel.onTouchListener)
        button.setOnClickListener(viewModel.onClickListener)
    }

    private fun boom() {
        Snackbar.make(button, getString(R.string.explosion), Snackbar.LENGTH_SHORT)
            .setAction("Action", null).show()
    }
}
