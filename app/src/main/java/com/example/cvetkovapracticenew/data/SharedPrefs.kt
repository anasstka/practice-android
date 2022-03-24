package com.example.cvetkovapracticenew.data

import android.content.Context
import android.content.SharedPreferences

class SharedPrefs(
    context: Context
) {

    private val TOKEN = "TOKEN"

    private var prefs: SharedPreferences =
        context.getSharedPreferences("SETTINGS", Context.MODE_PRIVATE)

    var token: Int
        get() = prefs.getInt(TOKEN, -1)
        set(value) = prefs.edit().putInt(TOKEN, value).apply()
}

var userToken: Int = -1