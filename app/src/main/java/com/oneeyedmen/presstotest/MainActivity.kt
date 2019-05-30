package com.oneeyedmen.presstotest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ViewModel(
            button = button,
            defaultText = getString(R.string.default_button_label),
            pressedText = getString(R.string.pressed_button_label),
            goBoom = this::boom
        )
    }

    private fun boom() {
        Snackbar.make(button, getString(R.string.explosion), Snackbar.LENGTH_SHORT)
            .setAction("Action", null).show()
    }
}
