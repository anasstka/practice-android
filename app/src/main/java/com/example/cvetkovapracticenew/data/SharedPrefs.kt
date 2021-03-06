package com.example.cvetkovapracticenew.data

import android.content.Context
import android.content.SharedPreferences

// класс для сохранения данных в память устройства
class SharedPrefs(
    context: Context
) {

    private val TOKEN = "TOKEN"
    private val USERNAME = "USERNAME"

    // объект настроек, с помощью которого происходит работа с данными
    private var prefs: SharedPreferences =
        context.getSharedPreferences("SETTINGS", Context.MODE_PRIVATE)

    // свойство для получения и сохранения токена
    var token: Int
        get() = prefs.getInt(TOKEN, -1)
        set(value) = prefs.edit().putInt(TOKEN, value).apply()

    // свойство для получения и сохранения имени пользователя
    var userName: String?
        get() = prefs.getString(USERNAME, "")
        set(value) = prefs.edit().putString(USERNAME, value).apply()
}

var userToken: Int = -1