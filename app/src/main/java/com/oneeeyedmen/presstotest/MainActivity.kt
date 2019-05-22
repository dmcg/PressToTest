package com.oneeeyedmen.presstotest

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener { view ->
            Snackbar.make(view, "BOOM!", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }
}
