package com.oneeyedmen.presstotest

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel = ViewModel(onButtonTextChanged = button::setText)
        button.setOnTouchListener(viewModel.onTouchListener)

        button.setOnClickListener { view ->
            Snackbar.make(view, getString(R.string.explosion), Snackbar.LENGTH_SHORT)
                .setAction("Action", null).show()
        }
    }
}
