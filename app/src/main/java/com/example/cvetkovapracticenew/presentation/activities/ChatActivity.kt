package com.example.cvetkovapracticenew.presentation.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.cvetkovapracticenew.R
import kotlinx.android.synthetic.main.activity_chat.*


class ChatActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        val movieId = intent.getStringExtra("movieId")
        val movieName = intent.getStringExtra("movieName")

        tv_movieName.text = movieName

        btn_back.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.btn_back -> {
                super.onBackPressed()
            }
            R.id.btn_send -> {
            }
        }
    }
}