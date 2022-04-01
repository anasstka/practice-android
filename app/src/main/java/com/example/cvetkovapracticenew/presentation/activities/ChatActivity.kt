package com.example.cvetkovapracticenew.presentation.activities

import android.os.AsyncTask
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cvetkovapracticenew.R
import com.example.cvetkovapracticenew.data.SharedPrefs
import com.example.cvetkovapracticenew.data.userToken
import com.example.cvetkovapracticenew.network.ApiHandler
import com.example.cvetkovapracticenew.network.ApiService
import com.example.cvetkovapracticenew.network.models.MessageBody
import com.example.cvetkovapracticenew.network.models.MessageResponse
import com.example.cvetkovapracticenew.presentation.adapters.ChatAdapter
import com.example.cvetkovapracticenew.presentation.view.dialog
import kotlinx.android.synthetic.main.activity_chat.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ChatActivity : AppCompatActivity(), View.OnClickListener {

    // сервис для отправки запросов
    var service: ApiService = ApiHandler.instance.service

    lateinit var movieId: String
    lateinit var movieName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        val sharedPrefs = SharedPrefs(applicationContext)
        userToken = sharedPrefs.token

        // получение параметров, переданных при открытии активити
        movieId = "4" //intent.getStringExtra("movieId") ?: ""
        movieName = intent.getStringExtra("movieName") ?: ""

        tv_movieName.text = movieName

        rv_chat.layoutManager =
            LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)

        getUserChat()

        btn_back.setOnClickListener(this)
        btn_send.setOnClickListener(this)
    }

    // обработчик кликов по кнопкам
    override fun onClick(view: View) {
        when (view.id) {
            R.id.btn_back -> {
                super.onBackPressed()
            }
            R.id.btn_send -> {
                sendMessage()
                et_message.text.clear()
            }
        }
    }

    // метод для асинхронной отправки запроса для получения списка сообщений чата
    private fun getUserChat() {
        AsyncTask.execute {
            service.getChatMessages(movieId.toInt())
                .enqueue(object : Callback<List<MessageResponse>> {
                    override fun onResponse(
                        call: Call<List<MessageResponse>>,
                        response: Response<List<MessageResponse>>
                    ) {
                        // при успешном выполнении запроса происходит заполнение адаптера данными
                        // и присвоение его списку
                        if (response.isSuccessful) {
                            val sharedPrefs = SharedPrefs(applicationContext)

                            val messages = response.body()
                            if (messages != null)
                                rv_chat.adapter = ChatAdapter(messages, sharedPrefs.userName ?: "")
                        } else {
                            dialog(applicationContext, "Ошибка сервера")
                        }
                    }

                    override fun onFailure(call: Call<List<MessageResponse>>, t: Throwable) {
                        dialog(applicationContext, "Ошибка")
                    }
                })
        }
    }

    // метод для асинхронной отправки запроса для добавления нового сообщения
    private fun sendMessage() {
        // если поле пустое - ошибка
        if (et_message.text.isBlank()) {
            dialog(this@ChatActivity, "Введите сообщение")
            return
        }

        AsyncTask.execute {
            service.sendMessage(movieId.toInt(), getMessageData())
                .enqueue(object : Callback<MessageResponse> {
                    override fun onResponse(
                        call: Call<MessageResponse>,
                        response: Response<MessageResponse>
                    ) {
                        // при успешном запросе обновление списка сообщений
                        if (response.isSuccessful) {
                            val message = response.body()
                            if (message != null) {
                                getUserChat()
                            }
                        } else {
                            dialog(applicationContext, "Ошибка сервера")
                        }
                    }

                    override fun onFailure(call: Call<MessageResponse>, t: Throwable) {
                        dialog(applicationContext, "Ошибка")
                    }
                })
        }
    }

    private fun getMessageData(): MessageBody {
        return MessageBody(
            text = et_message.text.toString()
        )
    }
}