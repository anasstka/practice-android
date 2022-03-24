package com.example.cvetkovapracticenew.presentation.activities

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.example.cvetkovapracticenew.R
import com.example.cvetkovapracticenew.data.SharedPrefs
import com.example.cvetkovapracticenew.data.userToken
import com.example.cvetkovapracticenew.presentation.fragments.CollectionsFragment
import com.example.cvetkovapracticenew.presentation.fragments.MainFragment
import com.example.cvetkovapracticenew.presentation.fragments.ProfileFragment
import com.example.cvetkovapracticenew.presentation.fragments.SelectionsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_main.*

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    val mainFragment = MainFragment()
    val collectionsFragment = CollectionsFragment()
    val selectionsFragment = SelectionsFragment()
    val profileFragment = ProfileFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharedPrefs = SharedPrefs(applicationContext)
        userToken = sharedPrefs.token

        bottom_navigation.setOnNavigationItemSelectedListener(this)
        bottom_navigation.selectedItemId = R.id.main_page
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.main_page -> {
                supportFragmentManager.beginTransaction().replace(R.id.mainFrame, mainFragment).commit()
                return true
            }
            R.id.selections_page -> {
                supportFragmentManager.beginTransaction().replace(R.id.mainFrame, selectionsFragment).commit()
                return true
            }
            R.id.collections_page -> {
                supportFragmentManager.beginTransaction().replace(R.id.mainFrame, collectionsFragment).commit()
                return true
            }
            R.id.profile_page -> {
                supportFragmentManager.beginTransaction().replace(R.id.mainFrame, profileFragment).commit()
                return true
            }
        }
        return false
    }
}