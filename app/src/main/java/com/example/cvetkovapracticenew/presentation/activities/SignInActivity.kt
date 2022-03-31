package com.example.cvetkovapracticenew.presentation.activities

import android.content.Intent
import android.content.SharedPreferences
import android.os.AsyncTask
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.example.cvetkovapracticenew.R
import com.example.cvetkovapracticenew.data.SharedPrefs
import com.example.cvetkovapracticenew.network.ApiHandler
import com.example.cvetkovapracticenew.network.ApiService
import com.example.cvetkovapracticenew.network.models.LoginBody
import com.example.cvetkovapracticenew.network.models.LoginResponse
import com.example.cvetkovapracticenew.network.models.UserResponse
import com.example.cvetkovapracticenew.presentation.view.Dialog
import kotlinx.android.synthetic.main.activity_sign_in.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInActivity : AppCompatActivity(), View.OnClickListener {

    var service: ApiService = ApiHandler.instance.service
    lateinit var sharedPrefs: SharedPrefs

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        sharedPrefs = SharedPrefs(applicationContext)

        btn_signIn.setOnClickListener(this)
        btn_openSignUp.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.btn_signIn -> {
                doSignIn()
            }
            R.id.btn_openSignUp -> {
                val intent = Intent(this, SignUpActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun doSignIn() {
        if (!isValidField())
            return

        AsyncTask.execute {
            service.login(getLoginData()).enqueue(object : Callback<LoginResponse> {
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    if (response.isSuccessful) {
                        sharedPrefs.token = response.body()?.token ?: -1

                        val intent = Intent(applicationContext, MainActivity::class.java)
                        startActivity(intent)
                        finish()

                    } else {
                        Dialog(this@SignInActivity, "Проблемы при авторизации")
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Dialog(this@SignInActivity, "Проблемы при авторизации")
                }
            })
        }
    }


    private fun getLoginData(): LoginBody {
        return LoginBody(
            email = et_signInEmail.text.toString(),
            password = et_signInPassword.text.toString()
        )
    }

    private fun isValidField(): Boolean {
        if (et_signInEmail.text.toString().isBlank()) {
            Dialog(this@SignInActivity, "Поле E-mail не может быть пустым")
            return false
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(et_signInEmail.text.toString()).matches()) {
            Dialog(this@SignInActivity, "Некорректный E-mail")
            return false
        }
        if (et_signInPassword.text.toString().isBlank()) {
            Dialog(this@SignInActivity, "Поле Пароль не может быть пустым")
            return false
        }
        return true
    }
}