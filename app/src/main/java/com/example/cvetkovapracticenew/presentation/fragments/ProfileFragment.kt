package com.example.cvetkovapracticenew.presentation.fragments

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.AsyncTask
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import coil.load
import com.example.cvetkovapracticenew.R
import com.example.cvetkovapracticenew.data.SharedPrefs
import com.example.cvetkovapracticenew.network.ApiHandler
import com.example.cvetkovapracticenew.network.ApiService
import com.example.cvetkovapracticenew.network.models.UserResponse
import com.example.cvetkovapracticenew.presentation.activities.SignInActivity
import com.example.cvetkovapracticenew.presentation.view.Dialog
import kotlinx.android.synthetic.main.fragment_profile.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProfileFragment : Fragment(), View.OnClickListener {

    var service: ApiService = ApiHandler.instance.service

    lateinit var tvUsername: TextView
    lateinit var tvEmail: TextView
    lateinit var ivAvatar: ImageView

    val GALLERY_REQUEST = 1


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        tvUsername = view.tv_username
        tvEmail = view.tv_email
        ivAvatar = view.iv_avatar

        view.btn_signOut.setOnClickListener(this)
        view.btn_changeAvatar.setOnClickListener(this)
        view.btn_chats.setOnClickListener(this)
        view.btn_history.setOnClickListener(this)
        view.btn_settings.setOnClickListener(this)

        getUser()

        return view
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.btn_signOut -> {
                val sharedPrefs = context?.let { SharedPrefs(it) }
                if (sharedPrefs != null) {
                    sharedPrefs.token = -1
                    val intent = Intent(context, SignInActivity::class.java)
                    startActivity(intent)
                    activity?.finish()
                }
            }
            R.id.btn_changeAvatar -> {
                val photoPickerIntent = Intent(Intent.ACTION_PICK)
                photoPickerIntent.type = "image/*"
                startActivityForResult(photoPickerIntent, GALLERY_REQUEST)
            }
            R.id.btn_chats -> {

            }
            R.id.btn_history -> {

            }
            R.id.btn_settings -> {

            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        var bitmap: Bitmap? = null

        when (requestCode) {
            GALLERY_REQUEST -> if (resultCode == RESULT_OK) {
                val selectedImage: Uri? = data?.data
                ivAvatar.setImageURI(selectedImage   )
            }
        }
    }

//    private fun changeAvatar(file: String) {
//        AsyncTask.execute {
//            service.changeUserAvatar().enqueue(object : Callback<List<UserResponse>> {
//                override fun onResponse(
//                    call: Call<List<UserResponse>>,
//                    response: Response<List<UserResponse>>
//                ) {
//                    if (response.isSuccessful) {
//                        val users = response.body()
//                        if (users != null) {
//                            val user = users[0]
//                            tvUsername.text = "${user.firstName} ${user.lastName}"
//                            tvEmail.text = user.email
//                            ivAvatar.load("http://cinema.areas.su/up/images/" + user.avatar)
//                        }
//                    } else if (response.code() == 400) {
//                        Toast.makeText(
//                            context,
//                            "Проблемы при загрузке данных",
//                            Toast.LENGTH_SHORT
//                        ).show();
//                    }
//                }
//
//                override fun onFailure(call: Call<List<UserResponse>>, t: Throwable) {
//                    Toast.makeText(
//                        context,
//                        "Проблемы при загрузке данных",
//                        Toast.LENGTH_SHORT
//                    ).show();
//                }
//            })
//        }
//    }

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
                            tvEmail.text = user.email
                            ivAvatar.load("http://cinema.areas.su/up/images/" + user.avatar)
                        }
                    } else {
                        Dialog(requireActivity(), "Проблемы при загрузке данных")
                    }
                }

                override fun onFailure(call: Call<List<UserResponse>>, t: Throwable) {
                    Dialog(requireActivity(), "Проблемы при загрузке данных")
                }
            })
        }
    }
}