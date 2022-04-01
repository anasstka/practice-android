package com.example.cvetkovapracticenew.presentation.activities

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.util.Patterns
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.cvetkovapracticenew.R
import com.example.cvetkovapracticenew.network.ApiHandler
import com.example.cvetkovapracticenew.network.ApiService
import com.example.cvetkovapracticenew.network.models.RegistrationBody
import com.example.cvetkovapracticenew.presentation.view.dialog
import kotlinx.android.synthetic.main.activity_sign_up.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SignUpActivity : AppCompatActivity(), View.OnClickListener {

    // сервис для отправки запросов
    var service: ApiService = ApiHandler.instance.service

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        btn_signUp.setOnClickListener(this)
        btn_openSignIn.setOnClickListener(this)
    }

    // обработчик кликов по кнопкам
    override fun onClick(view: View) {
        when (view.id) {
            R.id.btn_signUp -> {
                doSignUp()
            }
            R.id.btn_openSignIn -> {
                val intent = Intent(this, SignInActivity::class.java)
                startActivity(intent)
            }
        }
    }

    // метод для асинхронной отправки запроса для авторизации
    private fun doSignUp() {
        if (!isValidField())
            return

        AsyncTask.execute {
            service.register(getRegisterData()).enqueue(object : Callback<ResponseBody> {
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    // при успешном выполнении запроса переход на экран авторизации
                    if (response.isSuccessful) {
                        val intent = Intent(applicationContext, SignInActivity::class.java)
                        startActivity(intent)
                    } else {
                        dialog(this@SignUpActivity, "Проблемы при регистрации")
                    }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    dialog(this@SignUpActivity, "Проблемы при регистрации")
                }

            })
        }
    }

    // метод получения тела для запроса на регистрацию
    private fun getRegisterData(): RegistrationBody {
        return RegistrationBody(
            email = et_signUpEmail.text.toString(),
            password = et_signUpPassword.text.toString(),
            firstName = et_signUpName.text.toString(),
            lastName = et_signUpSurname.text.toString()
        )
    }

    // метод для проверки корректности данных в полях ввода
    private fun isValidField(): Boolean {
        if (et_signUpName.text.toString().isBlank()) {
            dialog(this@SignUpActivity, "Поле Имя не может быть пустым")
            return false
        }
        if (et_signUpSurname.text.toString().isBlank()) {
            dialog(this@SignUpActivity, "Поле Фамилия не может быть пустым")
            return false
        }
        if (et_signUpEmail.text.toString().isBlank()) {
            dialog(this@SignUpActivity, "Поле E-mail не может быть пустым")
            return false
        }
        // проверка, что email имеет корректную структуру
        if (!Patterns.EMAIL_ADDRESS.matcher(et_signUpEmail.text.toString()).matches()) {
            dialog(this@SignUpActivity, "Некорректный E-mail")
            return false
        }
        if (et_signUpPassword.text.toString().isBlank()) {
            dialog(this@SignUpActivity, "Поле Пароль не может быть пустым")
            return false
        }
        if (et_signUpPassword.text.toString() != et_signInRepeatPassword.text.toString()) {
            dialog(this@SignUpActivity, "Пароли не совпадают")
            return false
        }
        return true
    }
}