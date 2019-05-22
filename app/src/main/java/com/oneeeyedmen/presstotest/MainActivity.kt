package com.oneeeyedmen.presstotest

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.MotionEvent

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnTouchListener { view, event ->
            when (event.actionMasked) {
                MotionEvent.ACTION_DOWN -> button.text = "RELEASE TO DETONATE"
                MotionEvent.ACTION_UP -> button.text = "PRESS TO TEST"
            }
            false
        }

        button.setOnClickListener { view ->
            Snackbar.make(view, "BOOM!", Snackbar.LENGTH_SHORT)
                .setAction("Action", null).show()
        }
    }
}
