package com.example.ikinuki03

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.Toast

const val EXTRA_MESSAGE = "com.example.ikinuki03.MESSAGE"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    // implicit return value & 1 parameter as View
    fun onBtnClick(view : View) {
        val btnId = view.id

        val intent = Intent(this, MapsActivity::class.java)
            .apply {
                putExtra(EXTRA_MESSAGE, btnId)
            }


        startActivity(intent)
    }
}
