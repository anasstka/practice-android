package com.example.cvetkovapracticenew.presentation.fragments

import android.os.AsyncTask
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.example.cvetkovapracticenew.R
import com.example.cvetkovapracticenew.network.ApiHandler
import com.example.cvetkovapracticenew.network.ApiService
import com.example.cvetkovapracticenew.network.models.UserResponse
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_profile.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileFragment : Fragment() {

    var service: ApiService = ApiHandler.instance.service
    lateinit var tvUsername: TextView
    lateinit var ivEmail: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        tvUsername = view.tv_username
        ivEmail = view.tv_email

        getUser()

        return view
    }

    private fun getUser() {
        AsyncTask.execute {
            service.getUser().enqueue(object : Callback<List<UserResponse>> {
                override fun onResponse(
                    call: Call<List<UserResponse>>,
                    response: Response<List<UserResponse>>
                ) {
                    if (response.isSuccessful) {
                        val users = response.body()
                        if (users != null) {
                            val user = users[0]
                            tvUsername.text = "${user.firstName} ${user.lastName}"
                            ivEmail.text = user.email
                        }
                    } else if (response.code() == 400) {
                        Toast.makeText(
                            context,
                            "Проблемы при загрузке данных",
                            Toast.LENGTH_SHORT
                        ).show();
                    }
                }

                override fun onFailure(call: Call<List<UserResponse>>, t: Throwable) {
                    Toast.makeText(
                        context,
                        "Проблемы при загрузке данных",
                        Toast.LENGTH_SHORT
                    ).show();
                }
            })
        }
    }
}