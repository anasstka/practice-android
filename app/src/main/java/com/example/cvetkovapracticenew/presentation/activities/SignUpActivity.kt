package com.example.cvetkovapracticenew.presentation.activities

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.cvetkovapracticenew.R
import com.example.cvetkovapracticenew.network.ApiHandler
import com.example.cvetkovapracticenew.network.ApiService
import com.example.cvetkovapracticenew.network.models.RegistrationBody
import kotlinx.android.synthetic.main.activity_sign_up.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SignUpActivity : AppCompatActivity(), View.OnClickListener {

    var service: ApiService = ApiHandler.instance.service

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        btn_signUp.setOnClickListener(this)
        btn_openSignIn.setOnClickListener(this)
    }

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

    private fun doSignUp() {
        AsyncTask.execute {
            service.register(getRegisterData()).enqueue(object : Callback<ResponseBody> {
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    if (response.isSuccessful) {
                        Toast.makeText(
                            applicationContext,
                            "Регистрация прошла успешно!",
                            Toast.LENGTH_SHORT
                        ).show()

                        val intent = Intent(applicationContext, SignInActivity::class.java)
                        startActivity(intent)

                    } else if (response.code() == 400) {
                        Toast.makeText(
                            applicationContext,
                            "Проблемы при регитсрации",
                            Toast.LENGTH_SHORT
                        ).show();
                    }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Toast.makeText(
                        applicationContext,
                        "Проблемы при регитсрации",
                        Toast.LENGTH_SHORT
                    ).show();
                }

            })
        }
    }


    private fun getRegisterData(): RegistrationBody {
        return RegistrationBody(
            email = et_signUpEmail.text.toString(),
            password = et_signUpPassword.text.toString(),
            firstName = et_signUpName.text.toString(),
            lastName = et_signUpSurname.text.toString()
        )
    }
}