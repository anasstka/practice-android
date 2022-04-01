package com.example.cvetkovapracticenew.presentation.activities

import android.os.AsyncTask
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.cvetkovapracticenew.R
import com.example.cvetkovapracticenew.data.SharedPrefs
import com.example.cvetkovapracticenew.data.userToken
import com.example.cvetkovapracticenew.network.ApiHandler
import com.example.cvetkovapracticenew.network.ApiService
import com.example.cvetkovapracticenew.network.models.UserResponse
import com.example.cvetkovapracticenew.presentation.fragments.CollectionsFragment
import com.example.cvetkovapracticenew.presentation.fragments.MainFragment
import com.example.cvetkovapracticenew.presentation.fragments.ProfileFragment
import com.example.cvetkovapracticenew.presentation.fragments.SelectionsFragment
import com.example.cvetkovapracticenew.presentation.view.dialog
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    var service: ApiService = ApiHandler.instance.service

    val mainFragment = MainFragment()
    val collectionsFragment = CollectionsFragment()
    val selectionsFragment = SelectionsFragment()
    val profileFragment = ProfileFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharedPrefs = SharedPrefs(applicationContext)
        userToken = sharedPrefs.token

        getUser()

        bottom_navigation.setOnNavigationItemSelectedListener(this)
        bottom_navigation.selectedItemId = R.id.main_page
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
                            val sharedPrefs = SharedPrefs(applicationContext)
                            sharedPrefs.userName = "${user.firstName} ${user.lastName}"
                        }
                    } else {
                        dialog(this@MainActivity, "Проблемы при загрузке данных")
                    }
                }

                override fun onFailure(call: Call<List<UserResponse>>, t: Throwable) {
                    dialog(this@MainActivity, "Проблемы при загрузке данных")
                }
            })
        }
    }

    // метод переключения страниц через нижнюю навигационную панель
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.main_page -> {
                supportFragmentManager.beginTransaction().replace(R.id.mainFrame, mainFragment)
                    .commit()
                return true
            }
            R.id.selections_page -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.mainFrame, selectionsFragment).commit()
                return true
            }
            R.id.collections_page -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.mainFrame, collectionsFragment).commit()
                return true
            }
            R.id.profile_page -> {
                supportFragmentManager.beginTransaction().replace(R.id.mainFrame, profileFragment)
                    .commit()
                return true
            }
        }
        return false
    }
}