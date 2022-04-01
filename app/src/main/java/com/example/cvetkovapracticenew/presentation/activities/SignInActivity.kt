package com.example.cvetkovapracticenew.presentation.activities

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.util.Patterns
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.cvetkovapracticenew.R
import com.example.cvetkovapracticenew.data.SharedPrefs
import com.example.cvetkovapracticenew.network.ApiHandler
import com.example.cvetkovapracticenew.network.ApiService
import com.example.cvetkovapracticenew.network.models.LoginBody
import com.example.cvetkovapracticenew.network.models.LoginResponse
import com.example.cvetkovapracticenew.presentation.view.dialog
import kotlinx.android.synthetic.main.activity_sign_in.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInActivity : AppCompatActivity(), View.OnClickListener {

    // сервис для отправки запросов
    var service: ApiService = ApiHandler.instance.service
    lateinit var sharedPrefs: SharedPrefs

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        sharedPrefs = SharedPrefs(applicationContext)

        btn_signIn.setOnClickListener(this)
        btn_openSignUp.setOnClickListener(this)
    }

    // обработчик кликов по кнопкам
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

    // метод для асинхронной отправки запроса для авторизации
    private fun doSignIn() {
        if (!isValidField())
            return

        AsyncTask.execute {
            service.login(getLoginData()).enqueue(object : Callback<LoginResponse> {
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    // при успешном выполнении запроса токен пользователя сохраняется
                    // в память устройства и происходит переход на главный экран
                    if (response.isSuccessful) {
                        sharedPrefs.token = response.body()?.token ?: -1

                        val intent = Intent(applicationContext, MainActivity::class.java)
                        startActivity(intent)
                        finish()

                    } else {
                        dialog(this@SignInActivity, "Проблемы при авторизации")
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    dialog(this@SignInActivity, "Проблемы при авторизации")
                }
            })
        }
    }

    // метод получения тела для запроса на авторизацию
    private fun getLoginData(): LoginBody {
        return LoginBody(
            email = et_signInEmail.text.toString(),
            password = et_signInPassword.text.toString()
        )
    }

    // метод для проверки корректности данных в полях ввода
    private fun isValidField(): Boolean {
        if (et_signInEmail.text.toString().isBlank()) {
            dialog(this@SignInActivity, "Поле E-mail не может быть пустым")
            return false
        }
        // проверка, что email имеет корректную структуру
        if (!Patterns.EMAIL_ADDRESS.matcher(et_signInEmail.text.toString()).matches()) {
            dialog(this@SignInActivity, "Некорректный E-mail")
            return false
        }
        if (et_signInPassword.text.toString().isBlank()) {
            dialog(this@SignInActivity, "Поле Пароль не может быть пустым")
            return false
        }
        return true
    }
}