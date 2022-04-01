package com.example.cvetkovapracticenew.presentation.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.cvetkovapracticenew.R
import com.example.cvetkovapracticenew.data.SharedPrefs

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        // задержка загрузочного экрана 3 сек, а потом в зависимсости имеется ли токен
        // переход либо на экран авторизации, либо на главный экран
        Handler().postDelayed({
            val sharedPrefs = SharedPrefs(applicationContext)
            val intent =
                if (sharedPrefs.token == -1)
                    Intent(this, SignInActivity::class.java)
                else
                    Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }
}