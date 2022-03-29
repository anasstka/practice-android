package com.example.app_wear.presentation.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.app_wear.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : Activity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_openChats.setOnClickListener(this)
        btn_openCollections.setOnClickListener(this)
        btn_openFavorites.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.btn_openChats -> {
            }
            R.id.btn_openCollections -> {
                val intent = Intent(this, MoviesActivity::class.java)
                startActivity(intent)
            }
            R.id.btn_openFavorites -> {
            }
        }
    }
}